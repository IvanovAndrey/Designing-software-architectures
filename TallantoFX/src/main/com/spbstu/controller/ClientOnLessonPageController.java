package main.com.spbstu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;
import main.com.spbstu.project.ClientsOnLessons;
import main.com.spbstu.project.Lesson;

public class ClientOnLessonPageController {
    private Facade facade = Main.facade;
    private ClientsOnLessons col;
    private String login;
    private String name;
    private String status;
    private Lesson lesson;

    @FXML
    ChoiceBox choiceBox;
    @FXML
    TextArea textArea;
    @FXML
    private Button exitButton;
    @FXML
    private Button applyButton;

    public void setup(String login_, String name_, String status_, Lesson lesson_, ClientsOnLessons col_) {
        login = login_;
        name = name_;
        status = status_;
        lesson = lesson_;
        col = col_;
    }


    public void onClickExitButton() throws Exception {
        Main.lessonView(login, name, status,lesson);

    }

    public void onClickApplyButton() throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успех");
        String comment = textArea.getText();
        String status = String.valueOf(choiceBox.getValue());
        col.setCommetnary(comment);
        col.setStatus(status);
        facade.updateCON(login, col);
        alert.setHeaderText("Информация об ученике обновлена");
        alert.showAndWait();
    }
}
