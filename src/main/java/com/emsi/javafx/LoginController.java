package com.emsi.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("lstComptes.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setMinWidth(1170);
        stage.setMinHeight(700);
        stage.setMaxWidth(1170);
        stage.setMaxHeight(700);
        stage.setTitle("Comptes");
        stage.show();
        String username = usernameField.getText();
        String password = passwordField.getText();
        LoginApplication login =new LoginApplication();
        login.authenticate(username, password, messageLabel);

        Stage loginStage = (Stage) usernameField.getScene().getWindow();
        loginStage.close();
    }
}
