package org.savetovaliste.view.prikazi;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.savetovaliste.model.dao.FakultetDAO;
import org.savetovaliste.model.dao.OblastPsihoterapijeDAO;
import org.savetovaliste.model.dao.PsihoterapeutDAO;
import org.savetovaliste.model.dao.StepenStudijaDAO;
import org.savetovaliste.model.entity.Psihoterapeut;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PrikazSvihPsihoterapeutaWindow{

    private List<Psihoterapeut> psihoterapeuti;

    public void start(Stage stage) {
        TableView<Psihoterapeut> table = new TableView<>();

        TableColumn<Psihoterapeut, String> imeCol = new TableColumn<>("Ime");
        imeCol.setCellValueFactory(new PropertyValueFactory<>("ime"));

        TableColumn<Psihoterapeut, String> prezimeCol = new TableColumn<>("Prezime");
        prezimeCol.setCellValueFactory(new PropertyValueFactory<>("prezime"));

        TableColumn<Psihoterapeut, String> jmbgCol = new TableColumn<>("JMBG");
        jmbgCol.setCellValueFactory(new PropertyValueFactory<>("jmbg"));

        TableColumn<Psihoterapeut, LocalDate> datumRodjenjaCol = new TableColumn<>("Datum rodjenja");
        datumRodjenjaCol.setCellValueFactory(new PropertyValueFactory<>("datumRodjenja"));

        TableColumn<Psihoterapeut, String> prebivalisteCol = new TableColumn<>("Prebivaliste");
        prebivalisteCol.setCellValueFactory(new PropertyValueFactory<>("prebivaliste"));

        TableColumn<Psihoterapeut, String> brojTelefonaCol = new TableColumn<>("Broj telefona");
        brojTelefonaCol.setCellValueFactory(new PropertyValueFactory<>("brojTelefona"));

        TableColumn<Psihoterapeut, String> emailCol = new TableColumn<>("E-mail");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Psihoterapeut, String> fakultetCol = new TableColumn<>("Fakultet");
        fakultetCol.setCellValueFactory(cellData -> {
            int id = cellData.getValue().getFakultetId();
            String name = FakultetDAO.getFakultetName(id);
            return new ReadOnlyStringWrapper(name == null ? "" : name);
        });

        TableColumn<Psihoterapeut, String> stepenStudijaCol = new TableColumn<>("Stepen studija");
        stepenStudijaCol.setCellValueFactory(cellData -> {
            int id = cellData.getValue().getStepenStudijaId();
            String name = StepenStudijaDAO.getStepenStudijaName(id);
            return new ReadOnlyStringWrapper(name == null ? "" : name);
        });

        TableColumn<Psihoterapeut, LocalDate> datumSertifikacijeCol = new TableColumn<>("Datum sertifikacije");
        datumSertifikacijeCol.setCellValueFactory(new PropertyValueFactory<>("datumSertifikacije"));

        TableColumn<Psihoterapeut, String> oblastPsihoterapijeCol = new TableColumn<>("Oblast psihoterapije");
        oblastPsihoterapijeCol.setCellValueFactory(cellData -> {
            int id = cellData.getValue().getOblastPsihoterapijeId();
            String name = OblastPsihoterapijeDAO.getOblastPsihoterapijeName(id);
            return new ReadOnlyStringWrapper(name == null ? "" : name);
        });

        table.getColumns().addAll(imeCol, prezimeCol, jmbgCol, datumRodjenjaCol, prebivalisteCol,
                brojTelefonaCol, emailCol, fakultetCol, stepenStudijaCol, datumSertifikacijeCol, oblastPsihoterapijeCol
        );

        ObservableList<Psihoterapeut> data = FXCollections.observableArrayList(
                psihoterapeuti
        );

        table.setItems(data);
        VBox root = new VBox(table);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setTitle("Svi psihoterapeuti");
        stage.show();
    }
}
