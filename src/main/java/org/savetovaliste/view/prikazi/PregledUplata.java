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
import org.savetovaliste.model.dao.PrikazPlacanjaDAO;
import org.savetovaliste.model.entity.PregledBuducihKlijenataFunc;
import org.savetovaliste.model.entity.PregledUplataFunc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PregledUplata {
    private int psihoterapeutId;
    private static Stage stage;
    private List<PregledUplataFunc> listSeansaFunc;

    public PregledUplata(int Nulcina) {
        this.psihoterapeutId = Nulcina;
    }

    public void start(Stage stage) {
        if (this.stage == null)
            this.stage = stage;
        if (!stage.isShowing()) {
            initialize();
        }
    }

    private void initialize() {
        listSeansaFunc = new ArrayList<>();
        listSeansaFunc.addAll(PrikazPlacanjaDAO.getPrikazSeansiFunc(java.sql.Types.INTEGER));

        TableView<PregledUplataFunc> table = new TableView<>();

        TableColumn<PregledUplataFunc,Integer> klijentIdCol = new TableColumn<>("Klijent ID");
        klijentIdCol.setCellValueFactory(new PropertyValueFactory<>("klijentID"));

        TableColumn<PregledUplataFunc,String> klijentImeCol = new TableColumn<>("Klijent");
        klijentImeCol.setCellValueFactory(new PropertyValueFactory<>("klijentNaziv"));

        TableColumn<PregledUplataFunc,Integer> seansaIDCol = new TableColumn<>("Seansa ID");
        seansaIDCol.setCellValueFactory(new PropertyValueFactory<>("seansaID"));

        TableColumn<PregledUplataFunc, LocalDate> datumCol = new TableColumn<>("Datim seanse");
        datumCol.setCellValueFactory(new PropertyValueFactory<>("datum"));

        TableColumn<PregledUplataFunc,Integer> ukupnaCenaCol = new TableColumn<>("Ukupna Cena");
        ukupnaCenaCol.setCellValueFactory(new PropertyValueFactory<>("ukupnaCena"));

        TableColumn<PregledUplataFunc, Double> placenoCol = new TableColumn<>("Placeno RSD");
        placenoCol.setCellValueFactory(new PropertyValueFactory<>("placeno"));

        TableColumn<PregledUplataFunc,Double> dugCol = new TableColumn<>("Dug");
        dugCol.setCellValueFactory(new PropertyValueFactory<>("dug"));

        TableColumn<PregledUplataFunc, LocalDate> pocetakRokaCol = new TableColumn<>("Pocetak Roka");
        pocetakRokaCol.setCellValueFactory(new PropertyValueFactory<>("pocetakRoka"));

        TableColumn<PregledUplataFunc, String> prekoracenjeCol = new TableColumn<>("Prekoracenje Preko 30 Dana");
        prekoracenjeCol.setCellValueFactory(new PropertyValueFactory<>("prekoracenje"));

        TableColumn<PregledUplataFunc, Integer> prekoracenjeDanaCol = new TableColumn<>("Prekoracenje Dana");
        prekoracenjeDanaCol.setCellValueFactory(new PropertyValueFactory<>("prekoDana"));


        table.getColumns().addAll(klijentIdCol, klijentImeCol, seansaIDCol, datumCol, ukupnaCenaCol, placenoCol, dugCol, pocetakRokaCol, prekoracenjeCol, prekoracenjeDanaCol);

        ObservableList<PregledUplataFunc> data = FXCollections.observableArrayList(listSeansaFunc);

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
