package com.example.javafx1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button clickMe;

    int i = 0;
    @FXML
    protected void onHelloButtonClick() {
        i++;
        welcomeText.setText("i = "+ String.valueOf(i));
    }

    @FXML
    protected void onClickMeButtonClick(ActionEvent event) throws IOException {
        System.out.println("Wahoo! " + event);
        ChangeScene.changeScene(event, "wakaMole.fxml");
    }
}