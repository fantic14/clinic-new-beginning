package org.savetovaliste.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.savetovaliste.view.prikazi.OtvaranjeProzoraZaCuvanjePodatakaOObjavljivanjuWindow;

import java.sql.*;
import java.time.format.DateTimeFormatter;

import static org.savetovaliste.model.utility.JDBCUtils.getConnection;

public class CuvanjePodatakaOObjavljivanjuController implements EventHandler<ActionEvent> {

    OtvaranjeProzoraZaCuvanjePodatakaOObjavljivanjuWindow window;

    public CuvanjePodatakaOObjavljivanjuController(OtvaranjeProzoraZaCuvanjePodatakaOObjavljivanjuWindow otvaranjeProzoraZaCuvanjePodatakaOObjavljivanjuWindow) {
        this.window = otvaranjeProzoraZaCuvanjePodatakaOObjavljivanjuWindow;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!(window.getSeansaId().getValue() == null || window.getKada().getValue() == null || window.getKome().getText().isBlank()
        || window.getZasto().getText().isBlank())) {
            String query = "INSERT INTO psihoterapija.objavljeni_podatci (seansa_id, kada, kome, zasto) VALUES (?,?,?,?)";
            try (Connection connection = getConnection()) {
                connection.setAutoCommit(false);
                PreparedStatement osobaStmt = connection.prepareStatement(query);
                osobaStmt.setInt(1, window.getSeansaId().getValue());
                osobaStmt.setDate(2, Date.valueOf(window.getKada().getValue()));
                osobaStmt.setString(3, window.getKome().getText());
                osobaStmt.setString(4, window.getZasto().getText());
                osobaStmt.executeUpdate();
                connection.commit();
                window.getStage().close();
            } catch (SQLException e) {
                throw new RuntimeException("Error inserting objavljeni podatci", e);
            }
        } else
            window.getMessage().setText("Niste popunili sva polja!");
    }
}
