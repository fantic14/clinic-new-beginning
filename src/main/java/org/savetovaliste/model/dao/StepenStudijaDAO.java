package org.savetovaliste.model.dao;

import org.savetovaliste.model.entity.Fakultet;
import org.savetovaliste.model.entity.StepenStudija;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class StepenStudijaDAO {

    public static String getStepenStudijaName(int stepenStudijaId) {
        String query = "SELECT naziv FROM psihoterapija.stepen_studija WHERE stepen_studija_id = " + stepenStudijaId;
        String stepenStudijaName = null;
        try(PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery()){
            if (result.next()) {
                stepenStudijaName = result.getString("naziv");
            }
        }catch(SQLException e){
            throw new RuntimeException("Error fetching stepen studija name", e);
        }
        return stepenStudijaName;
    }

    public static List<StepenStudija> selectAllFromStepenStudija() {
        String query = "SELECT * FROM psihoterapija.stepen_studija";
        List<StepenStudija> stepenStudijalist = new ArrayList<>();
        try(PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery()) {
            while (result.next()){
                stepenStudijalist.add(new StepenStudija(result.getString("naziv")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching stepen studija list", e);
        }
        return stepenStudijalist;
    }
}
