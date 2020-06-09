package main.com.spbstu.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;
import main.com.spbstu.project.Complaint;
import main.com.spbstu.project.Request;
import main.com.spbstu.user.User;

import java.util.ArrayList;
import java.util.List;

public class UsersPageController {
    private Facade facade = Main.facade;
    private String login;
    private String name;
    private String status;

    @FXML
    private TableView<User> tableUsers;
    @FXML
    private TableColumn<User, String> colId;
    @FXML
    private TableColumn<User, String> colName;
    @FXML
    private TableColumn<User, String> colLogin;
    @FXML
    private TableColumn<User, String> colStatus;


    public void setup(String login_, String name_,String status_) {
        login = login_;
        name = name_;
        status = status_;
        List<User> userList = new ArrayList<User>();

        try {
            userList = facade.getUsers();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        ObservableList<User> items = FXCollections.observableArrayList();
        items.addAll(userList);
       tableUsers.setItems(items);
    }
    public void onClickBackButton() {
        Main.showAdminView(login,name,status);
    }
}
