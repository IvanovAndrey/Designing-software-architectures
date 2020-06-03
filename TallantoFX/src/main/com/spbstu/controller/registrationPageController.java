package main.com.spbstu.controller;

import main.com.spbstu.Main;

import main.com.spbstu.facade.Facade;
import javafx.fxml.FXML;
import javafx.scene.control.*;




public class registrationPageController {
    private Facade facade = Main.facade;
    @FXML private TextField loginField;
    @FXML private TextField nameField;
    @FXML private PasswordField password1Field;
    @FXML private PasswordField password2Field;
    @FXML private Button registerButton;
    @FXML private Button cancelButton;
    @FXML private ChoiceBox choiceBox;
    @FXML
    private void initialize() {
       /* for (Status status : Status.values()) {
            choiceBox.getItems().add(status.toString());
        }*/
    }
    @FXML private void onClickRegisterButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка");

        String login = loginField.getText();
        String name = nameField.getText();
        String pass1 = password1Field.getText();
        String pass2 = password2Field.getText();
        String status = choiceBox.getValue().toString();
        if (name.isEmpty()) {
            alert.setHeaderText("Введите имя");
            alert.showAndWait();
            return;
        } else if (login.isEmpty()) {
            alert.setHeaderText("Введите логин");
            alert.showAndWait();
        return;
        } else if (pass1.isEmpty()) {
            alert.setHeaderText("Введите пароль");
            alert.showAndWait();
            return;
        } else if (pass2.isEmpty()) {
            alert.setHeaderText("Повторите пароль");
            alert.showAndWait();
            return;
        } else if (!pass1.equals(pass2)) {
            alert.setHeaderText("Пароли не совпадают");
            alert.showAndWait();
            return;
        }

        try {
            facade.addUser(login, name, status, pass1);
            alert.setTitle("Поздравляем");
            alert.setHeaderText("Регистрация завершена");
            alert.showAndWait();
        } catch (Exception e) {
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            return;
        }
        Main.showStartView();
    }

    @FXML
    private void onClickCancelButton() {
        Main.showStartView();
    }



}
