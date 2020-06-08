package main.com.spbstu.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import main.com.spbstu.Main;
import main.com.spbstu.facade.Facade;
import main.com.spbstu.project.ClientsOnLessons;
import main.com.spbstu.project.Lesson;

import java.util.ArrayList;
import java.util.List;

public class LessonPageClientController {

        private Facade facade = Main.facade;
        private String login;
        private String name;
        private String status;
        private Lesson lesson;

        @FXML
        private Label teacherLabel;
        @FXML private Label idLabel;
        @FXML private Label dateLabel;
        @FXML private Label themeLabel;
        @FXML private Label statusLabel;
        @FXML private Label commentLabel;


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
            themeLabel.setText(String.valueOf(lesson.getTheme()));
            commentLabel.setText(String.valueOf(lesson.getCommentary()));
            statusLabel.setText(String.valueOf(lesson.getStatus()));

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

            ObservableList<ClientsOnLessons> items = FXCollections.observableArrayList();
            items.addAll(colList);
            clientsTable.setItems(items);
        }

        @FXML
        private void onClickExitButton() {
            Main.scheduleView(login, name, status);
        }

    }

