package main.com.spbstu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;
import main.com.spbstu.project.Notification;

import java.sql.SQLException;

public class ApproveNotificationPageController {
    private Facade facade = Main.facade;
    private String login;
    private String name;
    private String status;
    private Notification notification;

    @FXML
    private Label textLabel;
    @FXML
    private Label themeLabel;

    public void setup(String login_, String name_, String status_, Notification notification_) throws Exception {
        login = login_;
        name = name_;
        status = status_;
        notification = notification_;
        themeLabel.setText(notification.getTheme());
        textLabel.setText(notification.getText());
    }
    @FXML
    public void onClickApproveButton() throws SQLException {
        facade.setNotificationStatus(notification.getId(), "APPROVED");
        Main.notificationUserView(login, name, status);
    }
    @FXML
    public void onClickDenyButton() throws SQLException {
        facade.setNotificationStatus(notification.getId(), "DENIED");
        Main.notificationUserView(login, name, status);
    }

    @FXML
    private void onClickExitButton() throws SQLException {
        if(notification.getStatus().equals("NEW"))
        facade.setNotificationStatus(notification.getId(), "SEEN");
        Main.notificationUserView(login, name, status);
    }

}
