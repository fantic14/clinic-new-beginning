package org.savetovaliste.view.prikazi;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.savetovaliste.controller.RegistracijaPsihoterapeutaController;
import org.savetovaliste.model.dao.FakultetDAO;
import org.savetovaliste.model.dao.OblastPsihoterapijeDAO;
import org.savetovaliste.model.dao.StepenStudijaDAO;
import org.savetovaliste.model.entity.Fakultet;
import org.savetovaliste.model.entity.OblastPsihoterapije;
import org.savetovaliste.model.entity.StepenStudija;

@Getter
@Setter
public class RegistracijaPsihoterapeutaWindow {

    private TextField ime;
    private TextField prezime;
    private TextField jmbg;
    private DatePicker datumRodjenja;
    private TextField prebivaliste;
    private TextField brojTelefona;
    private TextField email;
    private ComboBox<Fakultet> fakultet;
    private ComboBox<StepenStudija> stepenStudija;
    private DatePicker datumSertifikacije;
    private ComboBox<OblastPsihoterapije> oblastPsihoterapije;
    private Button register;
    private Stage stage;

    public void start(Stage stage){
        this.stage = stage;
        initialize();
        VBox root = new VBox(ime,prezime,jmbg,datumRodjenja,prebivaliste,brojTelefona,email,fakultet,stepenStudija,datumSertifikacije,oblastPsihoterapije,register);
        root.setSpacing(10);
        Scene scene = new Scene(root, 500, 500);
        this.stage.setScene(scene);
        this.stage.setTitle("Registruj se");
        this.stage.show();
    }

    private void initialize() {
        this.ime = new TextField();
        this.ime.setPromptText("Ime");

        this.prezime = new TextField();
        this.prezime.setPromptText("Prezime");

        this.jmbg = new TextField();
        this.jmbg.setPromptText("JMBG");

        this.datumRodjenja = new DatePicker();
        this.datumRodjenja.setPromptText("Datum rodjenja");

        this.prebivaliste = new TextField();
        this.prebivaliste.setPromptText("Prebivaliste");

        this.brojTelefona = new TextField();
        this.brojTelefona.setPromptText("Broj telefona");

        this.email = new TextField();
        this.email.setPromptText("E-mail");

        this.fakultet = new ComboBox<>();
        this.fakultet.setPromptText("Fakultet");
        this.fakultet.getItems().addAll(FakultetDAO.selectAllFromFakultet());

        this.stepenStudija = new ComboBox<>();
        this.stepenStudija.setPromptText("Stepen studija");
        this.stepenStudija.getItems().addAll(StepenStudijaDAO.selectAllFromStepenStudija());

        this.datumSertifikacije = new DatePicker();
        this.datumSertifikacije.setPromptText("Datum sertifikacije");

        this.oblastPsihoterapije = new ComboBox<>();
        this.oblastPsihoterapije.setPromptText("Oblast psihoterapije");
        this.oblastPsihoterapije.getItems().addAll(OblastPsihoterapijeDAO.selectAllFromOblastPsihoterapije());

        this.register = new Button("Registruj se");
        this.register.setOnAction(new RegistracijaPsihoterapeutaController(this));
    }
}
