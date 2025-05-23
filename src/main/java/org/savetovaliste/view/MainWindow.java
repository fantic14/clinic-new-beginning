package org.savetovaliste.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.savetovaliste.controller.otvaranjeProzora.*;

import java.util.Objects;

public class MainWindow {

    @FXML
    public Button overviewTherapists;
    @FXML
    private Button login;
    @FXML
    public Button register;
    @FXML
    public Button profile;
    @FXML
    public Button clientAppointments;
    @FXML
    public Button sessionHistory;
    @FXML
    public Button futureSessions;
    @FXML
    public Button sessionNotes;
    @FXML
    public Button publishSessionData;
    @FXML
    public Button paymentOverview;

    @FXML
    public void initialize() {
        login.getStyleClass().addAll("btn", "btn-primary");
        login.setOnAction(new OtvaranjeProzoraZaLogovanjePsihoterapeutaController());
        register.getStyleClass().addAll("btn");
        register.setOnAction(new OtvaranjeProzoraZaRegistracijuPsihoterapeutaController());

        Image profileImage = new Image(Objects.requireNonNull(getClass().getResource("/imgs/profile.png")).toString());
        ImageView profileIcon = new ImageView(profileImage);
        profileIcon.setFitWidth(24);
        profileIcon.setFitHeight(24);
        profileIcon.setPreserveRatio(true);
        profile.setGraphic(profileIcon);
        profile.getStyleClass().addAll("btn", "btn-primary");
        profile.setOnAction(new PregledProfilaPsihoterapeutaController());
        overviewTherapists.getStyleClass().addAll("btn", "btn-info");
        overviewTherapists.setOnAction(new PrikazPsihoterapeutaController());
        clientAppointments.getStyleClass().addAll("btn", "btn-secondary");
        sessionHistory.getStyleClass().addAll("btn", "btn-info");
        sessionHistory.setOnAction(new OtvaranjeProzoraZaOdrzaneSeanse());
        futureSessions.getStyleClass().addAll("btn", "btn-info");
        futureSessions.setOnAction(new OtvaranjeProzoraZaBuduceSeanse());
        sessionNotes.getStyleClass().addAll("btn", "btn-warning");
        sessionNotes.setOnAction(new PregledBeleskiTestovaController());
        publishSessionData.getStyleClass().addAll("btn", "btn-danger");
        publishSessionData.setOnAction(new OtvaranjeProzoraZaCuvanjePodatakaOObjavljivanjuController());
        paymentOverview.getStyleClass().addAll("btn", "btn-primary");
    }

    @FXML
    protected void profileBtn() {}

    @FXML
    protected void loginBtn() {}

    @FXML
    protected void registerBtn() {}

    @FXML
    protected void reviewTherapists() {}

    @FXML
    protected void clientAppointmentsBtn() {}

    @FXML
    protected void sessionHistoryBtn() {}

    @FXML
    protected void futureSessionsBtn() {}

    @FXML
    protected void sessionNotesBtn() {}

    @FXML
    protected void publishSessionDataBtn() {}

    @FXML
    protected void paymentOverviewBtn() {}
}
