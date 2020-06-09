package main.com.spbstu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;

public class TeacherProfilePageController {
    private Facade facade = Main.facade;
    private String login;
    private String name;
    private String status;

    @FXML
    private Label nameLabel;
    @FXML private Label loginLabel;
    @FXML private Label statusLabel;

    @FXML private Button notificationButton;
    @FXML private Button requestButton;
    @FXML private Button scheduleButton;
    @FXML private Button exitButton;


    public void setup(String login_, String name_,String status_) {
        login = login_;
        name = name_;
        status = status_;
        loginLabel.setText(login);
        nameLabel.setText(name);
        statusLabel.setText(status);
    }

    @FXML
    private void onClickNotificationButton() {
        Main.notificationUserView(login, name, status);
    }

    @FXML
    private void onClickRequestButton() {
        Main.requestView(login, name, status);
    }
    @FXML
    private void onClickScheduleButton() {
        Main.scheduleView(login, name, status);
    }
    @FXML
    private void onClickExitButton() {
        Main.showStartView();
    }
}
