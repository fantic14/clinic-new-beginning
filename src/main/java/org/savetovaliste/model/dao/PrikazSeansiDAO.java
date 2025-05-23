package org.savetovaliste.model.dao;

import org.savetovaliste.model.entity.KlijentSeansaPsihoterapeut;
import org.savetovaliste.model.entity.PregledSeansiFunc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class PrikazSeansiDAO {
    public static List<PregledSeansiFunc> getPrikazSeansiFunc(int psihId) {
        String sql = "CALL Psihoterapija.sp_pregled_seansi(Psihoterapija.fn_rola(?), ?)";
        List<PregledSeansiFunc> list = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareCall(sql)) { // bolje prepareCall
            ps.setInt(1, psihId);  // ulaz u fn_rola
            ps.setInt(2, psihId);  // drugi parametar procedure

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new PregledSeansiFunc(
                            rs.getInt("seansa_id"),
                            rs.getString("beleske"),
                            rs.getInt("cena"),
                            rs.getDate("poslednja_promena_cene").toLocalDate(),
                            rs.getDate("datum").toLocalDate(),
                            rs.getTime("vreme").toLocalTime(),
                            rs.getInt("trajanje"),
                            rs.getInt("kandidat_id"),
                            rs.getInt("klijent_id"),
                            rs.getInt("psiholoski_test_id"),
                            rs.getString("naziv_psih_testa")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching pregled seansi", e);
        }
        return list;
    }
}
