package org.savetovaliste.view.prikazi;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.savetovaliste.controller.CuvanjePodatakaOObjavljivanjuController;

import java.util.List;

@Getter
@Setter
public class OtvaranjeProzoraZaCuvanjePodatakaOObjavljivanjuWindow {

    private List<Integer> seansaIdList;
    private ComboBox<Integer> seansaId;
    private DatePicker kada;
    private TextField kome;
    private TextField zasto;
    private Button save;
    private Label message;

    private static Stage stage;

    public OtvaranjeProzoraZaCuvanjePodatakaOObjavljivanjuWindow(List<Integer> seansaIdList) {
        this.seansaIdList = seansaIdList;
    }

    public void start(Stage stage){
        if (this.stage == null)
            this.stage = stage;
        if (!stage.isShowing()) {
            initialize();
        }
    }

    private void initialize() {
        seansaId = new ComboBox<>(FXCollections.observableArrayList(seansaIdList));
        kada = new DatePicker();
        kada.setPromptText("Kada");
        kome = new TextField();
        kome.setPromptText("Kome");
        zasto = new TextField();
        zasto.setPromptText("Zasto");
        save = new Button("Sacuvaj");
        save.setOnAction(new CuvanjePodatakaOObjavljivanjuController(this));
        message = new Label();
        VBox root = new VBox(seansaId, kada, kome, zasto, save, message);
        root.setSpacing(10);
        Scene scene = new Scene(root, 300, 200);
        this.stage.setScene(scene);
        this.stage.setTitle("Uloguj se");
        this.stage.show();
    }

    public static Stage getStage() {
        return stage;
    }
}
