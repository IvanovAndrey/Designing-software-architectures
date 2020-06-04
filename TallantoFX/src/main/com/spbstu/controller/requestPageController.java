package main.com.spbstu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;
import main.com.spbstu.user.User;

import java.sql.Date;
import java.util.Calendar;

public class RequestPageController {
    private Facade facade = Main.facade;
    private String login;
    private String name;
    private String status;

    @FXML
    private Button backButton;
    @FXML
    private Button sendButton;
    @FXML private CheckBox box1;
    @FXML private CheckBox box2;
    @FXML private CheckBox box3;
    @FXML private CheckBox box4;
    @FXML private CheckBox box5;
    @FXML private CheckBox box6;
    @FXML private CheckBox box7;


    public void setup(String login_, String name_,String status_) {
        login = login_;
        name = name_;
        status = status_;
    }

    @FXML
    private void onClickBackButton() {
        if(status.equals("client"))
            Main.showClientView(login, name, status);
        if(status.equals("teacher"))
            Main.showTeacherView(login, name, status);

    }
    @FXML
    private void onClickSendButton() throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успех");
        User user = facade.getCurrentUser(login);
        int id = user.getId();
        java.sql.Date dateOfSend = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String dates = "";
        if(box1.isSelected())
            dates = dates + "% ПН ";
        if(box2.isSelected())
            dates = dates + "% ВТ ";
        if(box3.isSelected())
            dates = dates + "% СР ";
        if(box4.isSelected())
            dates = dates + "% ЧТ ";
        if(box5.isSelected())
            dates = dates + "% ПТ ";
        if(box6.isSelected())
            dates = dates + "% СБ ";
        if(box7.isSelected())
            dates = dates + "% ВС ";
        facade.addRequest(id,dates,dateOfSend);
        alert.setHeaderText("Заявка была отправлена \n В случае, если это не первая ваша заявка - данные были обновлены");
        alert.showAndWait();
        if(status.equals("client"))
            Main.showClientView(login, name, status);
        if(status.equals("teacher"))
            Main.showTeacherView(login, name, status);
    }

}
