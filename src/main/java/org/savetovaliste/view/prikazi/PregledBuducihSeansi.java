package org.savetovaliste.view.prikazi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.savetovaliste.model.dao.PrikazBuducihSeansiDAO;
import org.savetovaliste.model.dao.PrikazSeansiDAO;
import org.savetovaliste.model.entity.PregledBuducihSeansiFunc;
import org.savetovaliste.model.entity.PregledSeansiFunc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PregledBuducihSeansi {

    private int psihoterapeutId;
    private static Stage stage;
    private List<PregledBuducihSeansiFunc> listSeansaBuducaFunc;

    public PregledBuducihSeansi(int psihoterapeutId) {
        this.psihoterapeutId = psihoterapeutId;
    }
    //'Ivana', 'Nikolic', '0506974123456'
    public void start(Stage stage) {
        if (this.stage == null)
            this.stage = stage;
        if (!stage.isShowing()) {
            initialize();
        }
    }

    private void initialize() {
        listSeansaBuducaFunc = new ArrayList<>();
        listSeansaBuducaFunc.addAll(PrikazBuducihSeansiDAO.getPrikazBuducihSeansi(psihoterapeutId));

        TableView<PregledBuducihSeansiFunc> table = new TableView<>();

        TableColumn<PregledBuducihSeansiFunc, Integer> seansaIdCol = new TableColumn<>("Seansa ID");
        seansaIdCol.setCellValueFactory(new PropertyValueFactory<>("seansaID"));

        TableColumn<PregledBuducihSeansiFunc,String> datumCol = new TableColumn<>("Datum");
        datumCol.setCellValueFactory(new PropertyValueFactory<>("datum"));

        TableColumn<PregledBuducihSeansiFunc, LocalTime> vremeCol = new TableColumn<>("Vreme");
        vremeCol.setCellValueFactory(new PropertyValueFactory<>("vreme"));

        TableColumn<PregledBuducihSeansiFunc,Integer> trajanjeCol = new TableColumn<>("Trajanje");
        trajanjeCol.setCellValueFactory(new PropertyValueFactory<>("trajanje"));

        TableColumn<PregledBuducihSeansiFunc,Integer> beleskeCol = new TableColumn<>("Beleske");
        beleskeCol.setCellValueFactory(new PropertyValueFactory<>("beleske"));

        TableColumn<PregledBuducihSeansiFunc,Integer> cenaCol = new TableColumn<>("Cena Po Satu");
        cenaCol.setCellValueFactory(new PropertyValueFactory<>("cenaSat"));

        TableColumn<PregledBuducihSeansiFunc,Integer> cenaUkupnoCol = new TableColumn<>("Cena Ukupno");
        cenaUkupnoCol.setCellValueFactory(new PropertyValueFactory<>("cenaUkupno"));

        TableColumn<PregledBuducihSeansiFunc,Integer> psihTestIdCol = new TableColumn<>("Test ID");
        psihTestIdCol.setCellValueFactory(new PropertyValueFactory<>("psiholoskiTestID"));

        TableColumn<PregledBuducihSeansiFunc,Integer> psihTestImeCol = new TableColumn<>("Test Ime");
        psihTestImeCol.setCellValueFactory(new PropertyValueFactory<>("nazivTesta"));

        TableColumn<PregledBuducihSeansiFunc,Integer> klijentIdCol = new TableColumn<>("Klijent ID");
        klijentIdCol.setCellValueFactory(new PropertyValueFactory<>("klijentID"));

        TableColumn<PregledBuducihSeansiFunc, LocalDate> klijentImeCol = new TableColumn<>("Klijent Ime");
        klijentImeCol.setCellValueFactory(new PropertyValueFactory<>("klijentIme"));

        TableColumn<PregledBuducihSeansiFunc, LocalDate> klijentPrezimeCol = new TableColumn<>("Klijent Prezime");
        klijentPrezimeCol.setCellValueFactory(new PropertyValueFactory<>("klijentPrezime"));

        table.getColumns().addAll(seansaIdCol, datumCol, vremeCol, trajanjeCol, beleskeCol, cenaCol, cenaUkupnoCol, psihTestIdCol, psihTestImeCol, klijentIdCol, klijentImeCol, klijentPrezimeCol);

        ObservableList<PregledBuducihSeansiFunc> data = FXCollections.observableArrayList(listSeansaBuducaFunc);

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
