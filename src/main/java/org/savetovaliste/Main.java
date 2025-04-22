package org.savetovaliste;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.savetovaliste.view.MainWindow;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainWindow mainWindow = new MainWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}