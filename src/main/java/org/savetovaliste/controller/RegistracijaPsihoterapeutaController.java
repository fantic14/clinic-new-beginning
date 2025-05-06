package org.savetovaliste.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.savetovaliste.model.dao.PsihoterapeutDAO;
import org.savetovaliste.model.entity.Psihoterapeut;
import org.savetovaliste.view.prikazi.RegistracijaPsihoterapeutaWindow;

public class RegistracijaPsihoterapeutaController implements EventHandler<ActionEvent> {

    private RegistracijaPsihoterapeutaWindow window;

    public RegistracijaPsihoterapeutaController(RegistracijaPsihoterapeutaWindow window) {
        this.window = window;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (!(window.getIme().getText().isBlank() || window.getPrezime().getText().isBlank()
        || window.getJmbg().getText().isBlank() || window.getEmail().getText().isBlank()
        || window.getDatumRodjenja().getValue() == null || window.getBrojTelefona().getText().isBlank()
        || window.getPrebivaliste().getText().isBlank() || window.getDatumSertifikacije().getValue() == null
        || window.getFakultet().getValue() == null || window.getOblastPsihoterapije().getValue() == null
        || window.getStepenStudija().getValue() == null)){
            PsihoterapeutDAO.insertPsihoterapeut(new Psihoterapeut(window.getIme().getText(),
                    window.getPrezime().getText(),
                    window.getJmbg().getText(),
                    window.getDatumRodjenja().getValue(),
                    window.getPrebivaliste().getText(),
                    window.getBrojTelefona().getText(),
                    window.getEmail().getText(),
                    window.getFakultet().getValue().getFakultetId(),
                    window.getStepenStudija().getValue().getStepenStudijaId(),
                    window.getDatumSertifikacije().getValue(),
                    window.getOblastPsihoterapije().getValue().getOblastPsihoterapijeId()));
            RegistracijaPsihoterapeutaWindow.getStage().close();
        } else
            window.getMessage().setText("Neispravni podaci!");
    }
}
