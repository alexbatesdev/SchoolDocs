package com.example.finalfour;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LeaderBoard implements Initializable {
    @FXML
    private Label leaderBoard;

    @FXML
    private Label userResults;

    @FXML
    private TextField nameInput;

    @FXML
    private Button nameSubmit;

    @FXML
    private Button parseHTML;

    @FXML
    private Button clearButton;

    public static String StrUserResults;
    public static int intUserScore;
    public static int intUserMissed;
    public static int intUserTricked;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        leaderBoard.setText(LeaderBoard.getLeaderBoard());
        userResults.setText(StrUserResults);
    }

    public static String getLeaderBoard() {
        MongoDeezNuts mdn = new MongoDeezNuts();
        ResultSet results = null;
        StringBuilder leaderBoard = new StringBuilder();
        try {
            results = mdn.getLeaderBoard();
            while (results.next()) {
                leaderBoard.append(results.getString("Username")).append(": ")
                            .append(results.getString("Score")).append(" points, ")
                            .append(results.getString("Missed")).append(" missed, tricked ")
                            .append(results.getString("Tricked")).append(" times.\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return leaderBoard.toString();
    }

    @FXML
    protected void onSubmit(Event event) throws MalformedURLException {
        String name = nameInput.getText();
        MongoDeezNuts mdn = new MongoDeezNuts();
        mdn.insertScore(name, intUserScore, intUserMissed, intUserTricked);
        leaderBoard.setText(LeaderBoard.getLeaderBoard());
    }

    @FXML
    protected void onClickParseHTML(Event event) {
        try {
            ChangeScene.changeScene(event, "ParseHTML.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onClearButton(Event event) {
        clearButton.setText("You foolish fool you!! You just deleted all the scores!!! Why did you click me??? Just for me to suffer???");
        MongoDeezNuts mdn = new MongoDeezNuts();
        try {
            mdn.clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        leaderBoard.setText("It's all gone... I hope you're happy.");
    }
}
