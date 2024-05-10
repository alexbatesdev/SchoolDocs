package me.alex.javafxlogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private Button logout;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeText.setText("Welcome " + HelloController.name);
    }

    @FXML
    protected void onLogout(ActionEvent event) throws IOException {
        ChangeScene.changeScene(event, "login.fxml");
    }
}
