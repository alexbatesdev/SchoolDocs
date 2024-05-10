package com.example.finalfour;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainMenuController {
    @FXML
    Button playBtn;

    @FXML
    protected void onPlayBtnClick(ActionEvent event) {
        System.out.println("Play button clicked");
        try {
            ChangeScene.changeScene(event, "ShokoSays.fxml");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
