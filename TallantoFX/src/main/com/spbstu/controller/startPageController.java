package main.com.spbstu.controller;

import main.com.spbstu.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import main.com.spbstu.Main;
import main.com.spbstu.user.User;

import java.io.IOException;

public class startPageController {
    private Facade facade = Main.facade;

    @FXML private Label errorLabel;
    @FXML private Label registerLabel;
    @FXML private TextField loginField;
    @FXML private PasswordField passwordField;
    @FXML private Button signInButton;
    @FXML private Button registerButton;

    @FXML
    private void initialize() {}

    @FXML
    private void onClickSignInButton() {
        String login = loginField.getText();
        String password = passwordField.getText();
        if (login.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Enter login and password");
            return;
        }
        try {
            facade.authenticate(login, password);
            User user = facade.getCurrentUser(login);
            String name = user.getName();
            String status = user.getStatus();
            if (status.equals("client")) {
                Main.showClientView(login, name, status);
            } else if (status.equals("admin")) {
                Main.showClientView(login, name, status);
            } else if (status.equals("teacher")) {
                Main.showClientView(login, name, status);
            }


        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    private void onCLickRegisterButton() throws IOException {
        Main.showRegistrationView();
    }
}
