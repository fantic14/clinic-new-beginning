package org.savetovaliste.model.dao;

import org.savetovaliste.model.entity.PsiholoskiTest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class PsiholoskiTestDAO {

    public static PsiholoskiTest selectPsiholoskiTestById(int id) {
        String query = "SELECT * FROM psihoterapija.psiholoski_test WHERE psiholoski_test_id = ?";
        PsiholoskiTest psiholoskiTest = null;
        try(PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()){
                psiholoskiTest = new PsiholoskiTest(
                        result.getInt("psiholoski_test_id"),
                        result.getString("oblast"),
                        result.getString("naziv"),
                        result.getInt("cena"),
                        result.getString("rezultat")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching psiholoski test", e);
        }
        return psiholoskiTest;
    }
}
