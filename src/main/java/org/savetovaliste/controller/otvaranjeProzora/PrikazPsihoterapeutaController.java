package org.savetovaliste.controller.otvaranjeProzora;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import org.savetovaliste.model.dao.PsihoterapeutDAO;
import org.savetovaliste.model.entity.Psihoterapeut;
import org.savetovaliste.view.prikazi.PrikazSvihPsihoterapeutaWindow;

import java.util.List;

public class PrikazPsihoterapeutaController implements EventHandler<ActionEvent> {
    @Override
    public void handle(ActionEvent actionEvent) {
        List<Psihoterapeut> p = PsihoterapeutDAO.selectAllFromPerson();

        PrikazSvihPsihoterapeutaWindow window = new PrikazSvihPsihoterapeutaWindow();
        window.setPsihoterapeuti(p);
        try{
            window.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException("Error starting PrikazSvihPsihoterapeutaWindow", e);
        }
    }
}
