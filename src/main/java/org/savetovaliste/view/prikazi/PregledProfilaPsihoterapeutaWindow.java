package org.savetovaliste.view.prikazi;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.savetovaliste.model.dao.PsihoterapeutDAO;
import org.savetovaliste.model.entity.Psihoterapeut;

import java.time.format.DateTimeFormatterBuilder;

public class PregledProfilaPsihoterapeutaWindow {

    private Psihoterapeut psihoterapeut;
    private static Stage stage;

    private Label ime;
    private Label prezime;
    private Label jmbg;
    private Label datumRodjenja;
    private Label prebivaliste;
    private Label brojTelefona;
    private Label email;
    private Label fakultet;
    private Label stepenStudija;
    private Label datumSertifikacije;
    private Label oblastPsihoterapije;

    public PregledProfilaPsihoterapeutaWindow(Psihoterapeut psihoterapeut) {
        this.psihoterapeut = psihoterapeut;
    }

    public void start(Stage s) {
        if (this.stage == null)
            this.stage = s;
        if (!stage.isShowing()) {
            this.ime = new Label("Ime: " + psihoterapeut.getIme());
            this.prezime = new Label("Prezime: " + psihoterapeut.getPrezime());
            this.jmbg = new Label("JMBG: " + psihoterapeut.getJmbg());
            DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();
            builder.appendPattern("dd.MM.yyyy");
            this.datumRodjenja = new Label("Datum rodjenja: " + psihoterapeut.getDatumRodjenja().format(builder.toFormatter()));
            this.prebivaliste = new Label("Prebivaliste: " + psihoterapeut.getPrebivaliste());
            this.brojTelefona = new Label("Broj telefona" + psihoterapeut.getBrojTelefona());
            this.email = new Label("E-mail: " + psihoterapeut.getEmail());
            this.fakultet = new Label("Fakultet: " + PsihoterapeutDAO.getFakultetFromPsihoterapeut(psihoterapeut).getNaziv());
            this.stepenStudija = new Label("Stepen studija: " + PsihoterapeutDAO.getStepenStudijafromPsihoterapeut(psihoterapeut).getNazivStepenStudija());
            this.datumSertifikacije = new Label("Datum sertifikacije: " + psihoterapeut.getDatumSertifikacije().format(builder.toFormatter()));
            this.oblastPsihoterapije = new Label("Oblast psihoterapije: " + PsihoterapeutDAO.getOblastPsihoterapijefromPsihoterapeut(psihoterapeut).getNaziv());

            VBox root = new VBox(ime, prezime, jmbg, datumRodjenja, prebivaliste, brojTelefona, email, fakultet, stepenStudija, datumSertifikacije, oblastPsihoterapije);
            root.setSpacing(10);
            stage.setScene(new Scene(root, 500, 500));
            stage.setTitle("Profil");
            stage.show();
        }
    }

    public static Stage getStage() {
        return stage;
    }
}
