package org.savetovaliste.view.prikazi;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.savetovaliste.controller.LogovanjePsihoterapeutaController;

@Getter
@Setter
public class LogovanjePsihoterapeutaWindow {

    private TextField ime;
    private TextField prezime;
    private TextField jmbg;
    private Button login;
    private Label message;
    private static Stage stage;

    public void start(Stage stage){
        if (this.stage == null)
            this.stage = stage;
        if (!stage.isShowing()) {
            initialize();
            VBox root = new VBox(ime, prezime, jmbg, login, message);
            root.setSpacing(10);
            Scene scene = new Scene(root, 300, 200);
            this.stage.setScene(scene);
            this.stage.setTitle("Uloguj se");
            this.stage.show();
        }
    }

    private void initialize() {
        this.ime = new TextField();
        this.ime.setPromptText("Ime");

        this.prezime = new TextField();
        this.prezime.setPromptText("Prezime");

        this.jmbg = new TextField();
        this.jmbg.setPromptText("JMBG");

        this.login = new Button("Uloguj se");
        this.login.setOnAction(new LogovanjePsihoterapeutaController(this));

        this.message = new Label();
    }

    public static Stage getStage() {
        return stage;
    }
}
