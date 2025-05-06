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
import org.savetovaliste.model.dao.KlijentSeansaPsihoterapeutDAO;
import org.savetovaliste.model.dao.ObjavljeniPodatciDAO;
import org.savetovaliste.model.dao.PsiholoskiTestDAO;
import org.savetovaliste.model.dao.SeansaDAO;
import org.savetovaliste.model.entity.KlijentSeansaPsihoterapeut;
import org.savetovaliste.model.entity.Psihoterapeut;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PregledBeleskiTestovaWindow {

    private int prijavljenPsihoterapeutId;
    private List<KlijentSeansaPsihoterapeut> kspList;
    private static Stage stage;

    public PregledBeleskiTestovaWindow(int prijavljenPsihoterapeutId) {
        this.prijavljenPsihoterapeutId = prijavljenPsihoterapeutId;
    }

    public void start(Stage stage) {
        if (this.stage == null)
            this.stage = stage;
        if (!stage.isShowing()) {
            initialize();
        }
    }

    private void initialize() {
        kspList = new ArrayList<>();
        kspList.addAll(KlijentSeansaPsihoterapeutDAO.getKspByPsihoterapeutId(prijavljenPsihoterapeutId));

        TableView<KlijentSeansaPsihoterapeut> table = new TableView<>();

        TableColumn<KlijentSeansaPsihoterapeut, Integer> seansaIdCol = new TableColumn<>("Seansa ID");
        seansaIdCol.setCellValueFactory(new PropertyValueFactory<>("seansaId"));

        TableColumn<KlijentSeansaPsihoterapeut, String> beleskeCol = new TableColumn<>("Beleske");
        beleskeCol.setCellValueFactory(cellData -> {
            int id = cellData.getValue().getSeansaId();
            String beleske = SeansaDAO.selectSeansaById(id).getBeleske();
            return new ReadOnlyStringWrapper(beleske == null ? "" : beleske);
        });

        TableColumn<KlijentSeansaPsihoterapeut, String> nazivPsiholoskogTestaCol = new TableColumn<>("Naziv psiholoskog testa");
        nazivPsiholoskogTestaCol.setCellValueFactory(cellData -> {
            int id = cellData.getValue().getPsiholoskiTestId();
            if (PsiholoskiTestDAO.selectPsiholoskiTestById(id) != null) {
                String naziv = PsiholoskiTestDAO.selectPsiholoskiTestById(id).getNaziv();
                return new ReadOnlyStringWrapper(naziv == null ? "" : naziv);
            }
            return new ReadOnlyStringWrapper("");
        });

        TableColumn<KlijentSeansaPsihoterapeut, String> kadaKomeZastoObjavljenTestCol = new TableColumn<>("Kada, kome i zasto\nje objavljen test");
        kadaKomeZastoObjavljenTestCol.setCellValueFactory(cellData -> {
            int seansaId = cellData.getValue().getSeansaId();
            if ( ObjavljeniPodatciDAO.selectObjavljeniPodatciBySeansaId(seansaId) != null ) {
                String kada = ObjavljeniPodatciDAO.selectObjavljeniPodatciBySeansaId(seansaId).getKada();
                String kome = ObjavljeniPodatciDAO.selectObjavljeniPodatciBySeansaId(seansaId).getKome();
                String zasto = ObjavljeniPodatciDAO.selectObjavljeniPodatciBySeansaId(seansaId).getZasto();
                String result = kada + ",\n" + kome + ",\n" + zasto;
                return new ReadOnlyStringWrapper(result == null ? "" : result);
            }
            return new ReadOnlyStringWrapper("");
        });

        table.getColumns().addAll(seansaIdCol, beleskeCol, nazivPsiholoskogTestaCol, kadaKomeZastoObjavljenTestCol);

        ObservableList<KlijentSeansaPsihoterapeut> data = FXCollections.observableArrayList(
                kspList
        );

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