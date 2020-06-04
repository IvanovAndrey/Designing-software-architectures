package main.com.spbstu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;

public class ComplaintPageController {
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

    @FXML
    private void onClickBackButton() {
       Main.showClientView(login, name, status);
    }

    @FXML
    private void onClickSendButton() {
        //to do
        //проверка был ли юзер на уроке
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка");

        String theme = themeField.getText();
        String text = textField.getText();
        if (idField.getText().isEmpty()){
            alert.setHeaderText("Заполните поле id урока");
            alert.showAndWait();
            return;
        }else if(theme.isEmpty()) {
            alert.setHeaderText("Заполните поле краткого описания проблемы");
            alert.showAndWait();
            return;}
        else if(text.replace("\n", "").isEmpty() ){
            alert.setHeaderText("Заполните поле полного описания проблемы");
            alert.showAndWait();
            return;
        }
        int idIncedent = Integer.parseInt(idField.getText());
        try {
            facade.addComplaint(idIncedent,theme,text);
            alert.setTitle("Успех");
            alert.setHeaderText("Жалоба отправлена");
            alert.showAndWait();
        } catch (Exception e) {
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            return;
        }
        Main.showClientView(login, name, status);
    }
}
