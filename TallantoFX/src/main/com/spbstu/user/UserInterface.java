package main.com.spbstu.user;

import main.com.spbstu.exceptions.DBConnectionException;
import main.com.spbstu.exceptions.NotAuthenticatedException;
import main.com.spbstu.project.Notification;
import main.com.spbstu.project.Request;

import java.sql.Date;
import java.sql.SQLException;

public interface UserInterface {
    void setId(int id_);
    int getId();
    User getUser();
    void checkAuthenticated() throws NotAuthenticatedException;

    Notification createNotification(int idFrom, int idTo, String status, String theme, String text) throws DBConnectionException;

    Notification findNotificationById(int id) throws SQLException;

    Request createRequest(int idUser, String dates, Date dateOfSend) throws DBConnectionException;

    void setNotificationStatus(int id, String status) throws SQLException;
}
