package main.com.spbstu.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;
import main.com.spbstu.project.Lesson;
import main.com.spbstu.project.Notification;
import main.com.spbstu.user.User;

import java.util.ArrayList;
import java.util.List;

public class NotificationUserPageController {
    private Facade facade = Main.facade;
    private String login;
    private String name;
    private String status;

    @FXML
    private TableView<Notification> notificationTable;
    @FXML
    private TableColumn<Notification, String> colId;
    @FXML
    private TableColumn<Notification, String> colFrom;
    @FXML
    private TableColumn<Notification, String> colStatus;
    @FXML
    private TableColumn<Notification, String> colTheme;
    @FXML
    private TableColumn<Notification, String> colBtn;
    @FXML
    private Button backButton;

    public void setup(String login_, String name_,String status_) throws Exception {
        login = login_;
        name = name_;
        status = status_;
        User user = facade.getCurrentUser(login);
        int id = user.getId();
        List<Notification> notificationList = new ArrayList<Notification>();
        try {
            notificationList = facade.getNotificationsForUser(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFrom.setCellValueFactory(new PropertyValueFactory<>("idTo"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colTheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colBtn.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        setBtnEditCellFactory();
        ObservableList<Notification> items = FXCollections.observableArrayList();
        items.addAll(notificationList);
        notificationTable.setItems(items);
    }

    private void setBtnEditCellFactory() {
        Callback<TableColumn<Notification, String>, TableCell<Notification, String>> btnDisplayCellFactory
                = new Callback<TableColumn<Notification, String>, TableCell<Notification, String>>() {
            @Override
            public TableCell<Notification, String> call(TableColumn<Notification, String> param) {
                final TableCell<Notification, String> cell = new TableCell<Notification, String>() {

                    final Button btn = new Button("Подробнее");
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        btn.getStyleClass().add("btn-default");
                        btn.setOnAction(event -> {
                            Notification notification = getTableView().getItems().get(getIndex());
                            try {
                                    Main.approveNotificationView(login, name, status, notification);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        setGraphic(btn);
                        setText(null);
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(btnDisplayCellFactory);
    }
    @FXML
    private void onClickBackButton() {
        if (status.equals("client"))
            Main.showClientView(login, name, status);
        else
            Main.showTeacherView(login, name, status);
    }
}
