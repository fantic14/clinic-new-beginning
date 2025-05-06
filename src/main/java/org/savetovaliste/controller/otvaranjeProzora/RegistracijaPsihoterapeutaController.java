package org.savetovaliste.controller.otvaranjeProzora;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.savetovaliste.view.prikazi.RegistracijaPsihoterapeutaWindow;

public class RegistracijaPsihoterapeutaController implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        RegistracijaPsihoterapeutaWindow window = new RegistracijaPsihoterapeutaWindow();
        try{
            window.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException("Error starting PrikazSvihPsihoterapeutaWindow", e);
        }
    }
}
