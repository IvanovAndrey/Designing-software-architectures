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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SchedulePageController {
    private Facade facade = Main.facade;
    private String login;
    private String name;
    private String status;

    @FXML private Button backButton;
    @FXML private Button nextButton;
    @FXML private Button prevButton;

    @FXML
    private TableView<Lesson> tableLessons;
    @FXML
    private TableColumn<Lesson, String> colId;
    @FXML
    private TableColumn<Lesson, Date> colDate;
    @FXML
    private TableColumn<Lesson, String> colTheme;
    @FXML
    private TableColumn<Lesson, String> colTeacher;
    @FXML
    private TableColumn<Lesson, String> colStatus;
    @FXML
    private TableColumn<Lesson, String> colBtnEdit;

    public void setup(String login_, String name_,String status_) {
        login = login_;
        name = name_;
        status = status_;
        List<Lesson> lessonList = new ArrayList<Lesson>();
        try {
           lessonList = facade.getLessons();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("dateOfLesson"));
        colTheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colTeacher.setCellValueFactory(new PropertyValueFactory<>("idTeacher"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colBtnEdit.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        setBtnEditCellFactory();

        ObservableList<Lesson> items = FXCollections.observableArrayList();
        items.addAll(lessonList);
        tableLessons.setItems(items);
    }
    private void setBtnEditCellFactory() {
        Callback<TableColumn<Lesson, String>, TableCell<Lesson, String>> btnDisplayCellFactory
                = new Callback<TableColumn<Lesson, String>, TableCell<Lesson, String>>() {
            @Override
            public TableCell<Lesson, String> call(TableColumn<Lesson, String> param) {
                final TableCell<Lesson, String> cell = new TableCell<Lesson, String>() {

                    final Button btn = new Button("Подробнее");
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        btn.getStyleClass().add("btn-default");
                            btn.setOnAction(event -> {
                                Lesson lesson = getTableView().getItems().get(getIndex());
                                Main.showStartView();
                            });
                            setGraphic(btn);
                            setText(null);
                    }
                };
                return cell;
            }
        };
        colBtnEdit.setCellFactory(btnDisplayCellFactory);
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
