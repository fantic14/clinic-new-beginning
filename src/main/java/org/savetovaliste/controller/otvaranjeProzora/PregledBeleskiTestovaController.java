package org.savetovaliste.controller.otvaranjeProzora;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.savetovaliste.controller.LogovanjePsihoterapeutaController;
import org.savetovaliste.model.dao.PsihoterapeutDAO;
import org.savetovaliste.model.entity.Psihoterapeut;
import org.savetovaliste.view.prikazi.PregledBeleskiTestovaWindow;

public class PregledBeleskiTestovaController implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        if (LogovanjePsihoterapeutaController.logedIn){
            Psihoterapeut p = PsihoterapeutDAO.selectFromPsihoterapeut(
                    LogovanjePsihoterapeutaController.ime,
                    LogovanjePsihoterapeutaController.prezime,
                    LogovanjePsihoterapeutaController.jmbg
            );
            PregledBeleskiTestovaWindow window = new PregledBeleskiTestovaWindow(p.getPsihoterapeutId());
            window.start(new Stage());
        } else {
            Stage stage = new Stage();
            VBox root = new VBox(new Label("Niste ulogovani!"));
            root.setSpacing(10);
            stage.setScene(new Scene(root));
            stage.show();
        }
    }
}
