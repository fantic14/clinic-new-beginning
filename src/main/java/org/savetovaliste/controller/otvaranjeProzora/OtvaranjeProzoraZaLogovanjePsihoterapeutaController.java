package org.savetovaliste.controller.otvaranjeProzora;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.savetovaliste.view.prikazi.LogovanjePsihoterapeutaWindow;

public class OtvaranjeProzoraZaLogovanjePsihoterapeutaController implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        LogovanjePsihoterapeutaWindow window = new LogovanjePsihoterapeutaWindow();
        try{
            window.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException("Error starting LogovanjePsihoterapeutaWindow", e);
        }
    }
}
