package org.savetovaliste.model.dao;

import org.savetovaliste.model.entity.Fakultet;
import org.savetovaliste.model.entity.OblastPsihoterapije;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class OblastPsihoterapijeDAO {
    public static String getOblastPsihoterapijeName(int oblastPsihoterapijeId) {
        String query = "SELECT naziv FROM psihoterapija.oblast_psihoterapije WHERE oblast_psihoterapije_id = " + oblastPsihoterapijeId;
        String oblastPsihoterapije = null;
        try(PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery()){
            if (result.next()) {
                oblastPsihoterapije = result.getString("naziv");
            }
        }catch(SQLException e){
            throw new RuntimeException("Error fetching oblast psihoterapije name", e);
        }
        return oblastPsihoterapije;
    }

    public static List<OblastPsihoterapije> selectAllFromOblastPsihoterapije() {
        String query = "SELECT * FROM psihoterapija.oblast_psihoterapije";
        List<OblastPsihoterapije> oblastPsihoterapijeList = new ArrayList<>();
        try(PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery()) {
            while (result.next()){
                oblastPsihoterapijeList.add(new OblastPsihoterapije(result.getInt("oblast_psihoterapije_id"),result.getString("naziv")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching oblast psihoterapije list", e);
        }
        return oblastPsihoterapijeList;
    }
}
