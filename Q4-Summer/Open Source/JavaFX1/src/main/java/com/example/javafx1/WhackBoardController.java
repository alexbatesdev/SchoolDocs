package com.example.javafx1;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class WhackBoardController implements Initializable {
    @FXML
    private Label scoreLabel;
    @FXML
    private ImageView img00;
    @FXML
    private ImageView img10;
    @FXML
    private ImageView img20;

    @FXML
    private ImageView img01;
    @FXML
    private ImageView img11;
    @FXML
    private ImageView img21;

    @FXML
    private ImageView img02;
    @FXML
    private ImageView img12;
    @FXML
    private ImageView img22;

    @FXML
    private GridPane gridPane0 = new GridPane();

    @FXML
    private Button results;

    @FXML
    private Label timeUp;

    private String[][] board = new String[3][3];

    public WhackBoardController() {
        System.out.println("Wumpus");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allMolesDown();

        update();
        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                toggleMole(Rando(0, 3), Rando(0, 3));
                update();
            }
        };

        t.schedule(task, new Date(),250);

        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                t.cancel();
                System.out.println("Woof");
                timeUp.visibleProperty().setValue(true);
                results.visibleProperty().setValue(true);
            }
        };

        t.schedule(task1, 30000);
    }

    private void allMolesDown() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = "MoleDown.png";
            }
        }
    }

    private void update() {
        System.out.println("Ding");
        try {
            scoreLabel.setText("Score: " + mailman.score);
            for (Node node : gridPane0.getChildren()) { // Iterating 1 too many times
                InputStream stream;
                Image image;
                String node_id = node.getId();
                if (node_id != null) {
                    switch (node_id) {
                        case "img00" -> {
                            stream = new FileInputStream(String.format("src/main/resources/com/example/javafx1/%s", board[0][0]));
                            image = new Image(stream);
                            img00.setImage(image);
                        }
                        case "img10" -> {
                            stream = new FileInputStream(String.format("src/main/resources/com/example/javafx1/%s", board[1][0]));
                            image = new Image(stream);
                            img10.setImage(image);
                        }
                        case "img20" -> {
                            stream = new FileInputStream(String.format("src/main/resources/com/example/javafx1/%s", board[2][0]));
                            image = new Image(stream);
                            img20.setImage(image);
                        }
                        case "img01" -> {
                            stream = new FileInputStream(String.format("src/main/resources/com/example/javafx1/%s", board[0][1]));
                            image = new Image(stream);
                            img01.setImage(image);
                        }
                        case "img11" -> {
                            stream = new FileInputStream(String.format("src/main/resources/com/example/javafx1/%s", board[1][1]));
                            image = new Image(stream);
                            img11.setImage(image);
                        }
                        case "img21" -> {
                            stream = new FileInputStream(String.format("src/main/resources/com/example/javafx1/%s", board[2][1]));
                            image = new Image(stream);
                            img21.setImage(image);
                        }
                        case "img02" -> {
                            stream = new FileInputStream(String.format("src/main/resources/com/example/javafx1/%s", board[0][2]));
                            image = new Image(stream);
                            img02.setImage(image);
                        }
                        case "img12" -> {
                            stream = new FileInputStream(String.format("src/main/resources/com/example/javafx1/%s", board[1][2]));
                            image = new Image(stream);
                            img12.setImage(image);
                        }
                        case "img22" -> {
                            stream = new FileInputStream(String.format("src/main/resources/com/example/javafx1/%s", board[2][2]));
                            image = new Image(stream);
                            img22.setImage(image);
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private int Rando(int min, int max) {
        Random rand = new Random();
        int intRandom = rand.nextInt(min, max);
        return intRandom;
    }
    @FXML
    void onClicked(MouseEvent event)  {
        System.out.println(event.getX() + " " + event.getY());
        update();
        String node_id = event.getPickResult().getIntersectedNode().getId();
        int x = Integer.parseInt(String.valueOf(node_id.charAt(3)));
        int y = Integer.parseInt(String.valueOf(node_id.charAt(4)));

        if (Objects.equals(board[x][y], "MoleUp.png")) {
            board[x][y] = "MoleWhack.jpg";
            mailman.score += 1;
            Timer t = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    board[x][y] = "MoleDown.png";
                }
            };
            t.schedule(task, 250);
        } else if (Objects.equals(board[x][y], "MoleDown.png")) {
            mailman.missed += 1;
        }
        update();
    }

    @FXML
    void resultsClicked(MouseEvent event) throws IOException {
        ChangeScene.changeScene(event, "results.fxml");
        System.out.println("Bonk");
    }

    private void toggleMole(int x, int y) {
        if (Objects.equals(board[x][y], "MoleDown.png")) {
            board[x][y] = "MoleUp.png";
        } else if (Objects.equals(board[x][y], "MoleUp.png")) {
            board[x][y] = "MoleDown.png";
        } else {
            board[x][y] = "MoleDown.png";
        }
    }
}
