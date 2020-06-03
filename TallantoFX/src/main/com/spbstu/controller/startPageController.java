package main.com.spbstu.controller;

import main.com.spbstu.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import main.com.spbstu.Main;
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
            Main.showClientView(login);
        } catch (Exception e) {
            errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    private void onCLickRegisterButton() throws IOException {
        Main.showRegistrationView();
    }
}
