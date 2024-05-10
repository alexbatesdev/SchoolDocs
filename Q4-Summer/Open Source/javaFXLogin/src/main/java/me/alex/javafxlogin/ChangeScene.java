package me.alex.javafxlogin;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ChangeScene {

    private static Stage stage;
    private static Scene scene;

    static void changeScene(Event event, String strFXMLFilename) throws IOException {
        URL url = new File("src/main/resources/me/alex/javafxlogin/" + strFXMLFilename).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)(((Node)event.getSource()).getScene().getWindow());
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
