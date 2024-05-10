package com.example.javafx1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Results implements Initializable {
    @FXML
    private Label scoreResults;

    @FXML
    private Label missedResults;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        scoreResults.setText("Score: " + mailman.score);
        missedResults.setText("Miss clicks: " + mailman.missed);
    }
}
