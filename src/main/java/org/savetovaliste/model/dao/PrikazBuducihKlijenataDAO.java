package org.savetovaliste.model.dao;

import org.savetovaliste.model.entity.PregledBuducihKlijenataFunc;
import org.savetovaliste.model.entity.PregledSeansiFunc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class PrikazBuducihKlijenataDAO {
    public static List<PregledBuducihKlijenataFunc> getPrikazSeansiFunc(int psihId) {
        String sql = "CALL Psihoterapija.sp_prijave_novih_klijenata(?)";
        List<PregledBuducihKlijenataFunc> list = new ArrayList<>();

        try (PreparedStatement ps = getConnection().prepareCall(sql)) {
            ps.setInt(1, psihId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new PregledBuducihKlijenataFunc(
                            rs.getInt("klijent_id"),
                            rs.getString("ime"),
                            rs.getString("prezime"),
                            rs.getString("email"),
                            rs.getString("broj_telefona"),
                            rs.getString("opis_problema"),
                            rs.getString("nacin_kontakta"),
                            rs.getDate("zakazani_datum").toLocalDate(),
                            rs.getTime("zakazano_vreme").toLocalTime(),
                            rs.getInt("trajanje_sati")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching pregled seansi", e);
        }
        return list;
    }
}
