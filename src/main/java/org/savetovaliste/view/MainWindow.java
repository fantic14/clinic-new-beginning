package org.savetovaliste.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindow {

    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/savetovaliste/view/main-window.fxml"));

    public MainWindow() throws IOException {
        fxmlLoader.setController(this);
        init();
    }

    @FXML
    protected void profileBtn(){

    }

    @FXML
    protected void loginBtn(){
        System.out.println("pressed");
    }

    @FXML
    protected void registerBtn(){
        System.out.println("pressed r");
    }

    private void init() throws IOException{
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setScene(scene);
        stage.show();
    }
}
