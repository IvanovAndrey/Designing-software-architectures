package main.com.spbstu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;
import main.com.spbstu.user.User;

public class NotificationCreatePageController {
    private Facade facade = Main.facade;
    private String login;
    private String name;
    private String status;

    @FXML
    private Button backButton;
    @FXML
    private Button sendButton;
    @FXML private TextField idField;
    @FXML private TextField themeField;
    @FXML private TextArea textField;

    public void setup(String login_, String name_,String status_) {
        login = login_;
        name = name_;
        status = status_;
    }
    public void onClickBackButton() {
        Main.showAdminView(login, name, status);
    }

    public void onClickSendButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка");

        String theme = themeField.getText();
        String text = textField.getText();
        if (idField.getText().isEmpty()){
            alert.setHeaderText("Заполните поле id ");
            alert.showAndWait();
            return;
        }else if(theme.isEmpty()) {
            alert.setHeaderText("Заполните поле темы");
            alert.showAndWait();
            return;}
        else if(text.replace("\n", "").isEmpty() ){
            alert.setHeaderText("Заполните поле полного описания");
            alert.showAndWait();
            return;
        }
        int idTo = Integer.parseInt(idField.getText());
        try {
            User admin = facade.getCurrentUser(login);
            facade.addNotification(admin.getLogin(), admin.getId(),idTo,"NEW",theme,text);
            alert.setTitle("Успех");
            alert.setHeaderText("Уведомление отправлено");
            alert.showAndWait();
        } catch (Exception e) {
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            return;
        }
        Main.showAdminView(login, name, status);
    }

}

