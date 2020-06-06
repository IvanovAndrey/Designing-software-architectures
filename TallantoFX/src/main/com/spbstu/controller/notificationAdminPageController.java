package main.com.spbstu.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;
import main.com.spbstu.project.Lesson;
import main.com.spbstu.project.Request;
import main.com.spbstu.user.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class NotificationAdminPageController {
    private Facade facade = Main.facade;
    private String login;
    private String name;
    private String status;

    @FXML
    private TableView<Request> requestTeacherTable;
    @FXML
    private TableColumn<Request, String> colId;
    @FXML
    private TableColumn<Request, Date> colDate;
    @FXML
    private TableColumn<Request, String> colLogin;
    @FXML
    private TableColumn<Request, String> colName;
    @FXML
    private TableColumn<Request, String> colStatus;
    @FXML
    private TableColumn<Request, String> colDays;
    @FXML
    private Button backButton;

    public void setup(String login_, String name_,String status_) {
        login = login_;
        name = name_;
        status = status_;
        List<Request> requestsList = new ArrayList<Request>();
        try {
            requestsList = facade.getRequests();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateOfSend"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colName.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("dates"));

        ObservableList<Request> items = FXCollections.observableArrayList();
        items.addAll(requestsList);
        requestTeacherTable.setItems(items);
    }

    @FXML
    private void onClickBackButton() {
        Main.showAdminView(login, name, status);
    }
}
