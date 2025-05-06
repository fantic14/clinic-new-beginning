package org.savetovaliste.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.savetovaliste.model.utility.JDBCUtils;
import org.savetovaliste.view.prikazi.LogovanjePsihoterapeutaWindow;

import java.sql.*;

public class LogovanjePsihoterapeutaController implements EventHandler<ActionEvent> {

    private LogovanjePsihoterapeutaWindow window;
    private static String ime, prezime, jmbg;

    public LogovanjePsihoterapeutaController(LogovanjePsihoterapeutaWindow window) {
        this.window = window;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!(window.getIme().getText().isBlank() || window.getPrezime().getText().isBlank()
        || window.getJmbg().getText().isBlank())){
            String query = "SELECT EXISTS (SELECT 1 FROM psihoterapija.psihoterapeut WHERE psihoterapeut_id = (SELECT osoba_id FROM psihoterapija.osoba WHERE ime = ? AND prezime = ? AND jmbg = ? LIMIT 1))";
            try (Connection connection = JDBCUtils.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(query)) {

                stmt.setString(1, window.getIme().getText());
                stmt.setString(2, window.getPrezime().getText());
                stmt.setString(3, window.getJmbg().getText());

                ResultSet rs = stmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 1) {
                    ime = window.getIme().getText();
                    prezime = window.getPrezime().getText();
                    jmbg = window.getJmbg().getText();
                    LogovanjePsihoterapeutaWindow.logedIn = true;
                    window.getStage().close();
                } else
                    window.getMessage().setText("Ne postoji psihoterapeut sa tim podacima!");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
