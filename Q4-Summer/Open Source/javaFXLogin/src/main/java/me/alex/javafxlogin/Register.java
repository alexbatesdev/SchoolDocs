package me.alex.javafxlogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.Objects;

public class Register {
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private Button register;
    @FXML
    private Button login;


    @FXML
    protected void onLogin(ActionEvent event) throws IOException {
        ChangeScene.changeScene(event, "login.fxml");
    }

    @FXML
    protected void onRegister(ActionEvent event) throws IOException {
        MongoDeezNuts mdn = new MongoDeezNuts();
        System.out.println("yah" + username.getText());

        if (!Objects.equals(username.getText(), "") && !Objects.equals(password.getText(), "") && !Objects.equals(firstName.getText(), "") && !Objects.equals(lastName.getText(), "") && !Objects.equals(email.getText(), "")) {
            if (!mdn.verifyUsername(username.getText())) {
                System.out.println("username is unique");
                mdn.addAccount(firstName.getText(), lastName.getText(), email.getText(), username.getText(), password.getText());
                HelloController.name = firstName.getText();
                ChangeScene.changeScene(event, "home.fxml");
            } else {
                System.out.println("username is not unique");
                register.setText("Username is not unique");
                register.setLayoutX(270.0 - 40);
            }
        } else {
            System.out.println("One or more fields are empty");
            register.setText("One or more fields are empty");
            register.setLayoutX(270.0 - 52);
        }

    }
}
