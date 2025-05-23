package org.savetovaliste.view.prikazi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.savetovaliste.model.dao.KlijentSeansaPsihoterapeutDAO;
import org.savetovaliste.model.dao.PrikazSeansiDAO;
import org.savetovaliste.model.entity.KlijentSeansaPsihoterapeut;
import org.savetovaliste.model.entity.PregledSeansiFunc;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PregledSeansi {

    private int psihoterapeutId;
    private static Stage stage;
    private List<PregledSeansiFunc> listSeansaFunc;

    public PregledSeansi(int psihoterapeutId) {
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
        listSeansaFunc.addAll(PrikazSeansiDAO.getPrikazSeansiFunc(psihoterapeutId));

        TableView<PregledSeansiFunc> table = new TableView<>();

        TableColumn<PregledSeansiFunc,Integer> seansaIdCol = new TableColumn<>("Seansa ID");
        seansaIdCol.setCellValueFactory(new PropertyValueFactory<>("seansaID"));

        TableColumn<PregledSeansiFunc,String> beleskeCol = new TableColumn<>("Beleske");
        beleskeCol.setCellValueFactory(new PropertyValueFactory<>("beleske"));

        TableColumn<PregledSeansiFunc,Integer> cenaCol = new TableColumn<>("Cena");
        cenaCol.setCellValueFactory(new PropertyValueFactory<>("cena"));

        TableColumn<PregledSeansiFunc, LocalDate> poslednjaPromenaCol = new TableColumn<>("Datum Poslednje Promene Cene");
        poslednjaPromenaCol.setCellValueFactory(new PropertyValueFactory<>("datumPromene"));

        TableColumn<PregledSeansiFunc,LocalDate> datumCol = new TableColumn<>("Datum");
        datumCol.setCellValueFactory(new PropertyValueFactory<>("datum"));

        TableColumn<PregledSeansiFunc, LocalTime> vremeCol = new TableColumn<>("Vreme");
        vremeCol.setCellValueFactory(new PropertyValueFactory<>("vreme"));

        TableColumn<PregledSeansiFunc,Integer> trajanjeCol = new TableColumn<>("Trajanje");
        trajanjeCol.setCellValueFactory(new PropertyValueFactory<>("trajanje"));

        TableColumn<PregledSeansiFunc,Integer> kandidatIdCol = new TableColumn<>("Kandidat ID");
        kandidatIdCol.setCellValueFactory(new PropertyValueFactory<>("kandidatID"));

        TableColumn<PregledSeansiFunc,Integer> klijentIdCol = new TableColumn<>("Klijent ID");
        klijentIdCol.setCellValueFactory(new PropertyValueFactory<>("klijentID"));

        TableColumn<PregledSeansiFunc,Integer> psihTestIdCol = new TableColumn<>("Test ID");
        psihTestIdCol.setCellValueFactory(new PropertyValueFactory<>("psiholoskiTestID"));

        TableColumn<PregledSeansiFunc,String> nazivTestaCol = new TableColumn<>("Naziv testa");
        nazivTestaCol.setCellValueFactory(new PropertyValueFactory<>("nazivTesta"));

        table.getColumns().addAll(seansaIdCol, beleskeCol, cenaCol, poslednjaPromenaCol, datumCol, vremeCol, trajanjeCol, kandidatIdCol, klijentIdCol, psihTestIdCol, nazivTestaCol);

        ObservableList<PregledSeansiFunc> data = FXCollections.observableArrayList(listSeansaFunc);

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
