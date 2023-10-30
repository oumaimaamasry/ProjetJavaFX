package com.emsi.javafx;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {
    private static final String USERNAME = "oumaima";
    private static final String PASSWORD = "oumaima";

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 200);
        stage.setTitle("Authentification");
        stage.setScene(scene);
        stage.show();
    }

    public void authenticate(String username, String password, Label messageLabel) throws IOException {

        if (username.equals(USERNAME) && password.equals(PASSWORD)) {

            messageLabel.setText("Authentification réussie !");
            messageLabel.setStyle("-fx-text-fill: green;");

        } else {
            messageLabel.setText("Nom d'utilisateur ou mot de passe incorrect !");
            messageLabel.setStyle("-fx-text-fill: red;");
        }

    }


}
