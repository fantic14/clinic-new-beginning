package org.savetovaliste.model.dao;

import org.savetovaliste.model.entity.KlijentSeansaPsihoterapeut;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class KlijentSeansaPsihoterapeutDAO {

    public static List<KlijentSeansaPsihoterapeut> getKspByPsihoterapeutId(int psihoterapeutId) {
        String query = "SELECT * FROM psihoterapija.klijent_seansa_psihoterapeut WHERE osoba_id = ?";
        List<KlijentSeansaPsihoterapeut> ksp = new ArrayList<>();
        try (PreparedStatement statement = getConnection().prepareStatement(query)){
            statement.setInt(1, psihoterapeutId);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                ksp.add(new KlijentSeansaPsihoterapeut(
                        result.getInt("klijent_id"),
                        result.getInt("seansa_id"),
                        result.getInt("osoba_id"),
                        result.getDate("datum").toLocalDate(),
                        result.getTime("vreme").toLocalTime(),
                        result.getInt("trajanje"),
                        result.getInt("psiholoski_test_id")
                ));
            }
        } catch (SQLException e){
            throw new RuntimeException("Error fetching klijent seansa psihoterapeut", e);
        }
        return ksp;
    }
}
