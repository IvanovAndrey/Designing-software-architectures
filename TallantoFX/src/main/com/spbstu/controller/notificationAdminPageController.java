package main.com.spbstu.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;
import main.com.spbstu.project.Complaint;
import main.com.spbstu.project.Lesson;
import main.com.spbstu.project.Notification;
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
/*
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
*/
    @FXML
    private TableView<Request> requestClientTable;
    @FXML
    private TableColumn<Request, String> colIdCl;
    @FXML
    private TableColumn<Request, Date> colDateCl;
    @FXML
    private TableColumn<Request, String> colNameCl;
    @FXML
    private TableColumn<Request, String> colStatusCl;
    @FXML
    private TableColumn<Request, String> colDaysCl;

    @FXML
    private TableView<Complaint> complaintTable;
    @FXML
    private TableColumn<Complaint, String> colIdc;
    @FXML
    private TableColumn<Complaint, String> colLessonc;
    @FXML
    private TableColumn<Complaint, String> colThemec;
    @FXML
    private TableColumn<Complaint, String> colBtnEditc;

    @FXML
    private TableView<Notification> notificationTable;
    @FXML
    private TableColumn<Notification, String> colIdn;
    @FXML
    private TableColumn<Notification, String> colNamen;
    @FXML
    private TableColumn<Notification, String> colStatusn;
    @FXML
    private TableColumn<Notification, String> colThemen;
    @FXML
    private TableColumn<Notification, String> colTextn;


    @FXML private Tab complaintTab;
    @FXML private Tab teachersTab;
    @FXML private Tab clientsTab;
    @FXML private Tab notificationTab;
    @FXML
    private Button backButton;


    public void setup(String login_, String name_,String status_) {
        login = login_;
        name = name_;
        status = status_;

    }


    @FXML
    private void onClickBackButton() {
        Main.showAdminView(login, name, status);
    }

    public void onSelectComplaintTab() {
        List<Complaint> complaintsList = new ArrayList<Complaint>();
        try {
            complaintsList = facade.getComplaints();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        colIdc.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLessonc.setCellValueFactory(new PropertyValueFactory<>("idIncedent"));
        colThemec.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colBtnEditc.setCellValueFactory(new PropertyValueFactory<>("text"));
        //setBtnEditCellFactory();
        ObservableList<Complaint> itemsc = FXCollections.observableArrayList();
        itemsc.addAll(complaintsList);
        complaintTable.setItems(itemsc);
    }
/*
    public void onSelectTeacherTab() {
        List<Request> requestsList = new ArrayList<Request>();
        try {
            requestsList = facade.getRequests();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateOfSend"));
        colName.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("dates"));

        ObservableList<Request> items = FXCollections.observableArrayList();
        items.addAll(requestsList);
        requestTeacherTable.setItems(items);
    }

    private void setBtnEditCellFactory() {
        Callback<TableColumn<Complaint, String>, TableCell<Complaint, String>> btnDisplayCellFactory
                = new Callback<TableColumn<Complaint, String>, TableCell<Complaint, String>>() {
            @Override
            public TableCell<Complaint, String> call(TableColumn<Complaint, String> param) {
                final TableCell<Complaint, String> cell = new TableCell<Complaint, String>() {

                    final Button btn = new Button("Подробнее");
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        btn.getStyleClass().add("btn-default");
                        btn.setOnAction(event -> {
                            Complaint complaint = getTableView().getItems().get(getIndex());
                            //Main.lessonView(login, name, status, lesson);
                        });
                        setGraphic(btn);
                        setText(null);
                    }
                };
                return cell;
            }
        };
        colBtnEditc.setCellFactory(btnDisplayCellFactory);
    }
*/
    public void onSelectClientsTab() {
        List<Request> requestsList = new ArrayList<Request>();
        try {
            requestsList = facade.getRequests();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        colIdCl.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDateCl.setCellValueFactory(new PropertyValueFactory<>("dateOfSend"));
        colNameCl.setCellValueFactory(new PropertyValueFactory<>("idUser"));
        colStatusCl.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDaysCl.setCellValueFactory(new PropertyValueFactory<>("dates"));

        ObservableList<Request> items = FXCollections.observableArrayList();
        items.addAll(requestsList);
        requestClientTable.setItems(items);
    }

    public void onSelectNotificationTab() {
        List<Notification> notificationList = new ArrayList<Notification>();
        try {
            notificationList = facade.getNotifications();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        colIdn.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNamen.setCellValueFactory(new PropertyValueFactory<>("idTo"));
        colStatusn.setCellValueFactory(new PropertyValueFactory<>("status"));
        colThemen.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colTextn.setCellValueFactory(new PropertyValueFactory<>("text"));
        ObservableList<Notification> items = FXCollections.observableArrayList();
        items.addAll(notificationList);
        notificationTable.setItems(items);
    }
}
