package main.com.spbstu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;

public class SchedulePageController {
    private Facade facade = Main.facade;
    private String login;
    private String name;
    private String status;

    @FXML
    private Button backButton;

    public void setup(String login_, String name_,String status_) {
        login = login_;
        name = name_;
        status = status_;
    }

    @FXML
    private void onClickBackButton() {
        if(status.equals("admin"))
            Main.showAdminView(login, name, status);
        if(status.equals("client"))
            Main.showClientView(login, name, status);
        if(status.equals("teacher"))
            Main.showTeacherView(login, name, status);
}
}