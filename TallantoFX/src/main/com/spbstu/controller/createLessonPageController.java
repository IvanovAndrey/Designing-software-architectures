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
        String[] clients;
        String delimetr ="-";
        boolean allUsersExist = true;
        clients = clientString.replace(" ", "").split(delimetr);
        for (int i = 0; i <clients.length; i++) {
            if (!(facade.isUserExist(clients[i])) || !(facade.getCurrentUser(clients[i]).getStatus().equals("client")))
                allUsersExist = false;
        }
         if(theme.isEmpty()) {
            alert.setHeaderText("Укажите тему урока");
            alert.showAndWait();
            return;}
        else if(teacher.isEmpty() ){
            alert.setHeaderText("Укажите преподавателя");
            alert.showAndWait();
            return;
        }else if(clientString.isEmpty() ){
            alert.setHeaderText("Укажите учеников");
            alert.showAndWait();
            return;
        }else if (clients.length > 5){
            alert.setHeaderText("Количество учеников превышает 5");
            alert.showAndWait();
            return;
        }else if(!(facade.isUserExist(teacher))){
             alert.setHeaderText("Преподавателя с таким логином не существует");
             alert.showAndWait();
             return;
         }else if (!allUsersExist){
             alert.setHeaderText("Ошибка в логинах учеников.\n Проверьте формат ввода и наличие учеников");
             alert.showAndWait();
             return;
         }

        try {
            if(!(facade.addLesson(teacher, theme, date))){
                alert.setHeaderText("Не удалось создать урок. Возможные причины: \n 1. Данный пользователь не обладает стаусом преподавателя.\n 2. Урок у этого учителя в эту дату уже существует");
                alert.showAndWait();
                return;
            }
            for (int i = 0; i <clients.length; i++) {
                facade.addClientOnLesson(teacher, date, clients[i]);
            }
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
