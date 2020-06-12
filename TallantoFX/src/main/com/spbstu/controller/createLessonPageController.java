package main.com.spbstu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;

public class CreateLessonPageController {
    private Facade facade = Main.facade;
    private String login;
    private String name;
    private String status;

    @FXML
    private Button backButton;
    @FXML
    private Button createButton;
    @FXML private TextField themeField;
    @FXML private TextField teacherField;
    @FXML private TextField clientsField;
    @FXML private ChoiceBox choiceBox;


    public void setup(String login_, String name_,String status_) {
        login = login_;
        name = name_;
        status = status_;
    }

    @FXML
    private void onClickCreateButton() throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ошибка");
        String date = choiceBox.getValue().toString();
        String theme = themeField.getText();
        String teacher = teacherField.getText();
        String clientString = clientsField.getText();

        try {
            facade.addLesson(login, teacher, theme, date, clientString);
            alert.setTitle("Успех");
            alert.setHeaderText("Урок создан");
            alert.showAndWait();
        } catch (Exception e) {
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            return;
        }
        Main.showAdminView(login, name, status);
    }

    @FXML
    private void onClickBackButton() {
        Main.showAdminView(login, name, status);
    }
}
