package me.alex.javafxlogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloController extends Node { // Controller class for the login.fxml file
    public static String name;
    @FXML
    private Label welcomeText;

    @FXML
    private Button register;

    @FXML
    private Button login;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");

        MongoDeezNuts mdn = new MongoDeezNuts();
        ResultSet results = null;
        try {
            results = mdn.getCreds("Muce");
            while (results.next()) {
                System.out.println(results.getInt("id") + " " + results.getString("FirstName"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    @FXML
    protected void onLogin(ActionEvent event) throws IOException {
        String inputUsername = username.getCharacters().toString();
        String inputPassword = password.getCharacters().toString();

        System.out.println("Attempting login with username: " + inputUsername + " and password: " + inputPassword);

        MongoDeezNuts mdn = new MongoDeezNuts();
        ResultSet results = null;

        try {
            results = mdn.getCreds(inputUsername);
            while (results.next()) {
                if (results.getString("Password").equals(inputPassword)) {
                    System.out.println("Login Successful");
                    name = results.getString("FirstName");
                    ChangeScene.changeScene(event, "home.fxml");
                } else {
                    System.out.println("Login Failed");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }




    @FXML
    protected void onRegister(ActionEvent event) throws IOException {
        System.out.println("Switching to Register");
        ChangeScene.changeScene(event, "register.fxml");
    }
}