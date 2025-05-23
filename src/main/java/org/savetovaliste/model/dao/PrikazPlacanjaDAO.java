package org.savetovaliste.model.dao;

import org.savetovaliste.model.entity.PregledBuducihKlijenataFunc;
import org.savetovaliste.model.entity.PregledUplataFunc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class PrikazPlacanjaDAO {
    public static List<PregledUplataFunc> getPrikazSeansiFunc(int psihId) {
        String sql = "CALL Psihoterapija.sp_pregled_uplata_dugovanja(?)";
        List<PregledUplataFunc> list = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareCall(sql)) {
            ps.setInt(1, psihId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new PregledUplataFunc(
                            rs.getInt("klijentID"),
                            rs.getString("klijent"),
                            rs.getInt("seansaID"),
                            rs.getDate("datum_seanse").toLocalDate(),
                            rs.getInt("ukupna_cena_rsd"),
                            rs.getDouble("placeno_rsd"),
                            rs.getDouble("dug_rsd"),
                            rs.getDate("datum_pocetka_roka").toLocalDate(),
                            rs.getString("prekoracen_rok"),
                            rs.getInt("dana_preko_30")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching pregled seansi", e);
        }
        return list;
    }
}
