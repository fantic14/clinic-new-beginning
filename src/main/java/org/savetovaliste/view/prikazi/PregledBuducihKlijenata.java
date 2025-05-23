package org.savetovaliste.view.prikazi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.savetovaliste.model.dao.PrikazBuducihKlijenataDAO;
import org.savetovaliste.model.dao.PrikazSeansiDAO;
import org.savetovaliste.model.entity.PregledBuducihKlijenataFunc;
import org.savetovaliste.model.entity.PregledSeansiFunc;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PregledBuducihKlijenata {
    private int psihoterapeutId;
    private static Stage stage;
    private List<PregledBuducihKlijenataFunc> listSeansaFunc;

    public PregledBuducihKlijenata(int psihoterapeutId) {
        this.psihoterapeutId = psihoterapeutId;
    }
    //'Petra', 'Kovacevic', '2402199023456'
    public void start(Stage stage) {
        if (this.stage == null)
            this.stage = stage;
        if (!stage.isShowing()) {
            initialize();
        }
    }

    private void initialize() {
        listSeansaFunc = new ArrayList<>();
        listSeansaFunc.addAll(PrikazBuducihKlijenataDAO.getPrikazSeansiFunc(psihoterapeutId));

        TableView<PregledBuducihKlijenataFunc> table = new TableView<>();

        TableColumn<PregledBuducihKlijenataFunc,Integer> klijentIdCol = new TableColumn<>("Klijent ID");
        klijentIdCol.setCellValueFactory(new PropertyValueFactory<>("klijentID"));

        TableColumn<PregledBuducihKlijenataFunc,String> klijentImeCol = new TableColumn<>("Klijent Ime");
        klijentImeCol.setCellValueFactory(new PropertyValueFactory<>("klijentIme"));

        TableColumn<PregledBuducihKlijenataFunc,String> klijentPrezimeCol = new TableColumn<>("Klijent Prezime");
        klijentPrezimeCol.setCellValueFactory(new PropertyValueFactory<>("klijentPrezime"));

        TableColumn<PregledBuducihKlijenataFunc, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<PregledBuducihKlijenataFunc,String> telefonCol = new TableColumn<>("Telefon");
        telefonCol.setCellValueFactory(new PropertyValueFactory<>("telefon"));

        TableColumn<PregledBuducihKlijenataFunc, String> problemCol = new TableColumn<>("Problem");
        problemCol.setCellValueFactory(new PropertyValueFactory<>("problem"));

        TableColumn<PregledBuducihKlijenataFunc,String> nacinKontaktaCol = new TableColumn<>("Nacin Kontakta");
        nacinKontaktaCol.setCellValueFactory(new PropertyValueFactory<>("nacinKontakta"));

        TableColumn<PregledBuducihKlijenataFunc, LocalDate> datumCol = new TableColumn<>("Datum");
        datumCol.setCellValueFactory(new PropertyValueFactory<>("datum"));

        TableColumn<PregledBuducihKlijenataFunc, LocalTime> vremeCol = new TableColumn<>("Vreme");
        vremeCol.setCellValueFactory(new PropertyValueFactory<>("vreme"));

        TableColumn<PregledBuducihKlijenataFunc,Integer> trajanjeCol = new TableColumn<>("Trajanje");
        trajanjeCol.setCellValueFactory(new PropertyValueFactory<>("trajanje"));

        table.getColumns().addAll(klijentIdCol, klijentImeCol, klijentPrezimeCol, emailCol, telefonCol, problemCol, nacinKontaktaCol, datumCol, vremeCol, trajanjeCol);

        ObservableList<PregledBuducihKlijenataFunc> data = FXCollections.observableArrayList(listSeansaFunc);

        table.setItems(data);
        VBox root = new VBox(table);
        Scene scene = new Scene(root, 700, 300);
        stage.setScene(scene);
        stage.setTitle("Beleske i testovi");
        stage.show();
    }
    public static Stage getStage() {
        return stage;
    }
}
