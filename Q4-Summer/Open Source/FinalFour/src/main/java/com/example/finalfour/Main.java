package com.example.finalfour;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    //TODO: My four things

    //TODO MySQL CRUD operations (show 3 types) - Create, Read, Delete
    //TODO JavaFX - (click button, load image, save message to label, load 2nd form) - Yes
    //TODO Read JSON, output to JavaFX or Console - GameSteps.json and GameStep.java

    // Separate page
    //TODO Retrieve and parse webpage HTML (find all hyperlinks in HTML)

    //MYSQL QUERIES
    //CREATE TABLE `javafxlogin`.`leaderboard` (
    //  `idLeaderBoard` INT NOT NULL AUTO_INCREMENT,
    //  `Username` VARCHAR(45) NULL,
    //  `Score` INT NULL,
    //  `Missed` INT NULL,
    //  `Tricked` INT NULL,
    //  PRIMARY KEY (`idLeaderBoard`),
    //  UNIQUE INDEX `idLeaderBoard_UNIQUE` (`idLeaderBoard` ASC) VISIBLE);
}