package org.savetovaliste.model.dao;

import org.savetovaliste.model.entity.ObjavljeniPodatci;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class ObjavljeniPodatciDAO {

    public static ObjavljeniPodatci selectObjavljeniPodatciBySeansaId(int seansaId){
        String query = "SELECT * FROM psihoterapija.objavljeni_podatci WHERE seansa_id = ?";
        ObjavljeniPodatci objavljeniPodatci = null;
        try (PreparedStatement statement = getConnection().prepareStatement(query)){
            statement.setInt(1, seansaId);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                objavljeniPodatci = new ObjavljeniPodatci(
                        result.getInt("objavljeni_podatci_id"),
                        result.getInt("seansa_id"),
                        result.getString("kada"),
                        result.getString("kome"),
                        result.getString("zasto")
                );
            }
        } catch (SQLException e){
            throw new RuntimeException("Error fetching objavljeni podatci", e);
        }
        return objavljeniPodatci;
    }
}
