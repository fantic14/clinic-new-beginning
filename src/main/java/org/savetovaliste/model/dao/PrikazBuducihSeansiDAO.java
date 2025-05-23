package org.savetovaliste.model.dao;

import org.savetovaliste.model.entity.PregledBuducihSeansiFunc;
import org.savetovaliste.model.entity.PregledSeansiFunc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class PrikazBuducihSeansiDAO {
    public static List<PregledBuducihSeansiFunc> getPrikazBuducihSeansi(int psihId) {
        String sql = "CALL Psihoterapija.sp_pregled_buducih_seansi(?)";
        List<PregledBuducihSeansiFunc> list = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareCall(sql)) {
            ps.setInt(1, psihId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new PregledBuducihSeansiFunc(
                            rs.getInt("seansa_id"),
                            rs.getDate("datum").toLocalDate(),
                            rs.getTime("vreme").toLocalTime(),
                            rs.getInt("trajanje"),
                            rs.getString("beleske"),
                            rs.getInt("cena_po_satu"),
                            rs.getInt("cena_ukupno"),
                            rs.getInt("psiholoski_test_id"),
                            rs.getString("naziv_testa"),
                            rs.getInt("klijent_id"),
                            rs.getString("klijent_ime"),
                            rs.getString("klijent_prezime")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching pregled buducih seansi", e);
        }
        return list;
    }
}
