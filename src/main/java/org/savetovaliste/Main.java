package org.savetovaliste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/savetovaliste/view/main-window.fxml"));
        Scene scene = new Scene(loader.load(), 800, 500);
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setScene(scene);
        stage.setTitle("Savetovaliste");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}