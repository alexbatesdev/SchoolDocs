package com.example.finalfour;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class ShokoSays implements Initializable {
    @FXML
    private ImageView topLeft;

    @FXML
    private ImageView topRight;

    @FXML
    private ImageView botLeft;

    @FXML
    private ImageView botRight;

    @FXML
    private Label prompt;

    @FXML
    private Button NextScene;

    public String target;
    public String falseTarget;
    public int score;
    public int missed;
    public int tricked;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timer t = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                step();
            }
        };
        t.schedule(task, new Date(),1500);

        TimerTask task1 = new TimerTask() {
            int i = 0;
            @Override
            public void run() {
                if (i >= 30) {
                    t.cancel();
                    System.out.println("Time's up!");
                    LeaderBoard.StrUserResults = "Score: " + score + " points, " + missed + " missed, tricked " + tricked + " times.";
                    LeaderBoard.intUserScore = score;
                    LeaderBoard.intUserMissed = missed;
                    LeaderBoard.intUserTricked = tricked;
                    NextScene.setVisible(true);
                } else {
                    if (i % 2 == 0) {
                        System.out.println("Tick");
                    } else {
                        System.out.println("Tock");
                    }
                    i++;
                }
            }
        };
        t.schedule(task1, new Date(),1000);
    }

    private void step() {
        final ArrayList<GameStep> steps = GetJSON();
        String prePath = "C:\\Users\\mbates\\OneDrive\\School Docs\\Q4-Summer\\Open Source\\FinalFour\\src\\main\\resources\\com\\example\\finalfour\\";
        Image img;
        int i = (int) (Math.random() * steps.size());

        prompt.setText(steps.get(i).getLabelText()); //This line throws an error, but like not actually? Wrapping it in a try catch never triggers the catch

        prompt.setStyle("-fx-text-fill: " + steps.get(i).getLabelColor());


        img = new Image( prePath + steps.get(i).getColors()[0] + ".png");
        topLeft.setImage(img);
        img = new Image( prePath + steps.get(i).getColors()[1] + ".png");
        topRight.setImage(img);
        img = new Image( prePath + steps.get(i).getColors()[2] + ".png");
        botRight.setImage(img);
        img = new Image( prePath + steps.get(i).getColors()[3] + ".png");
        botLeft.setImage(img);
        target = steps.get(i).getCorrect();
        falseTarget = steps.get(i).getFalseTarget();
    }

    public static ArrayList<GameStep> GetJSON() {
        ObjectMapper mapper = new ObjectMapper();
        URL url = null;
        ArrayList<GameStep> gameSteps = new ArrayList<GameStep>();
        try {
            url = new URL("file:game.json");
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }

        try {
            JsonNode root = mapper.readTree(url);
            System.out.println(root.get("Game"));
            root.withArray("Game").forEach(node -> {
//                System.out.println(node.get("Colors"));
//                System.out.println(node.get("LabelColor"));
//                System.out.println(node.get("LabelText"));
//                System.out.println(node.get("Correct"));
                ArrayList<String> colors = new ArrayList<String>();
                node.get("Colors").forEach(color -> {
                    colors.add(color.asText());
                });

                gameSteps.add(new GameStep(colors.toArray(new String[0]), node.get("LabelColor").asText(), node.get("LabelText").asText(), node.get("Correct").asText(), node.get("False").asText()));
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return gameSteps;
    }

    @FXML
    protected void onClick(Event event) {
        String tagret = ((ImageView)event.getSource()).getId();
        if (tagret.equals(this.target) && !NextScene.visibleProperty().getValue()) {
            System.out.println("Correct!");
            score++;
        } else if (tagret.equals(this.falseTarget) && !NextScene.visibleProperty().getValue()) {
            System.out.println("Tricked!");
            tricked++;
            missed++;
        } else if (!NextScene.visibleProperty().getValue()) {
            System.out.println("Incorrect!");
            missed++;
        }
    }

    @FXML
    protected void onClickNextScene(Event event) {
        try {
            ChangeScene.changeScene(event, "LeaderBoard.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}