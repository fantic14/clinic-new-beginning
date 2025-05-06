package org.savetovaliste.model.dao;

import org.savetovaliste.model.entity.Seansa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class SeansaDAO {

    public static Seansa selectSeansaById(int id) {
        String query = "SELECT * FROM psihoterapija.seansa WHERE seansa_id = ?";
        Seansa seansa = null;

        try (PreparedStatement statement = getConnection().prepareStatement(query)){
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                seansa = new Seansa(
                        result.getInt("seansa_id"),
                        result.getString("beleske"),
                        result.getInt("cena"),
                        result.getDate("poslednja_promena_cene").toLocalDate(),
                        result.getBoolean("priznao_krivicno_delo")
                );
            }
        } catch (SQLException e){
            throw new RuntimeException("Error fetching seansa", e);
        }
        return seansa;
    }
}
