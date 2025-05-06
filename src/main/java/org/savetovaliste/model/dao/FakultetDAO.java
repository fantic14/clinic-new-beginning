package org.savetovaliste.model.dao;

import org.savetovaliste.model.entity.Fakultet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class FakultetDAO {

    public static String getFakultetName(int fakultetId) {
        String query = "SELECT naziv FROM psihoterapija.fakultet WHERE fakultet_id = " + fakultetId;
        String fakultetName = null;
        try(PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery()){
            if (result.next()) {
                fakultetName = result.getString("naziv");
            }
        }catch(SQLException e){
            throw new RuntimeException("Error fetching fakultet name", e);
        }
        return fakultetName;
    }

    public static List<Fakultet> selectAllFromFakultet() {
        String query = "SELECT * FROM psihoterapija.fakultet";
        List<Fakultet> fakultetList = new ArrayList<>();
        try(PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet result = statement.executeQuery()) {
            while (result.next()){
                fakultetList.add(new Fakultet(result.getInt("fakultet_id"),result.getString("naziv"), result.getInt("univerzitet_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching fakultet list", e);
        }
        return fakultetList;
    }
}
