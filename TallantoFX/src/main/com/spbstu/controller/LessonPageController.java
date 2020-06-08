package main.com.spbstu.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;
import main.com.spbstu.project.ClientsOnLessons;
import main.com.spbstu.project.Lesson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LessonPageController {
    private Facade facade = Main.facade;
    private String user;
    private String login;
    private String name;
    private String status;
    private Lesson lesson;

    @FXML private Label teacherLabel;
    @FXML private Label idLabel;
    @FXML private Label dateLabel;
    @FXML private TextField commentField;
    @FXML private TextField themeField;
    @FXML private ChoiceBox statusBox;

    @FXML
    private TableView<ClientsOnLessons> clientsTable;
    @FXML
    private TableColumn<ClientsOnLessons, String> colId;
    @FXML
    private TableColumn<ClientsOnLessons, String> colLogin;
    @FXML
    private TableColumn<ClientsOnLessons, String> colName;
    @FXML
    private TableColumn<ClientsOnLessons, String> colStatus;
    @FXML
    private TableColumn<ClientsOnLessons, String> colComment;
    @FXML
    private TableColumn<ClientsOnLessons, String> colBtnEdit;

    @FXML
    private Button exitButton;
    @FXML
    private Button applyButton;
    @FXML
    public void setup(String login_, String name_,String status_, Lesson lesson_) throws Exception {
        login = login_;
        name = name_;
        status = status_;
        lesson = lesson_;
        teacherLabel.setText(facade.findLiginById(lesson.getIdTeacher()));
        idLabel.setText(String.valueOf(lesson.getId()));
        dateLabel.setText(String.valueOf(lesson.getDateOfLesson()));
        themeField.setText(String.valueOf(lesson.getTheme()));
        commentField.setText(String.valueOf(lesson.getCommentary()));
        statusBox.setValue(String.valueOf(lesson.getStatus()));

        List<ClientsOnLessons> colList = new ArrayList<ClientsOnLessons>();
        try {
            colList = facade.getCOL();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colLogin.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        colName.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colComment.setCellValueFactory(new PropertyValueFactory<>("commentary"));
        colBtnEdit.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        setBtnEditCellFactory();

        ObservableList<ClientsOnLessons> items = FXCollections.observableArrayList();
        items.addAll(colList);
        clientsTable.setItems(items);
    }

    @FXML
    private void onClickExitButton() {
        Main.scheduleView(login, name, status);
    }

    @FXML
    public void onClickApplyButton() throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успех");
        lesson.setStatus(String.valueOf(statusBox.getValue()));
        lesson.setTheme(themeField.getText());
        lesson.setCommetnary(commentField.getText());
        facade.updateLesson(lesson);
        alert.setHeaderText("Урок обновлен");
        alert.showAndWait();

    }


    private void setBtnEditCellFactory() {
        Callback<TableColumn<ClientsOnLessons, String>, TableCell<ClientsOnLessons, String>> btnDisplayCellFactory
                = new Callback<TableColumn<ClientsOnLessons, String>, TableCell<ClientsOnLessons, String>>() {
            @Override
            public TableCell<ClientsOnLessons, String> call(TableColumn<ClientsOnLessons, String> param) {
                final TableCell<ClientsOnLessons, String> cell = new TableCell<ClientsOnLessons, String>() {

                    final Button btn = new Button("Изменить");
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        btn.getStyleClass().add("btn-default");
                        btn.setOnAction(event -> {
                            ClientsOnLessons col = getTableView().getItems().get(getIndex());
                            Lesson lessonOld = new Lesson(lesson);
                            try {
                                Main.clientOnLessonView(login, name, status, lessonOld, col);
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
        colBtnEdit.setCellFactory(btnDisplayCellFactory);
    }
}
