package main.com.spbstu.facade;

import main.com.spbstu.project.*;
import main.com.spbstu.user.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface Facade {
    void addUser(String login, String name, String role, String password) throws Exception;
    void authenticate(String login, String password) throws Exception;
    boolean isUserExist (String login) throws Exception;

    String findLiginById(int id) throws Exception;

    void signOut(String user) throws Exception;
    User getCurrentUser(String login) throws Exception;
    void addComplaint(String login, String idIncedent, String theme, String text) throws Exception;

    void addNotification(String login, int idFrom, int idTo, String status, String theme, String text) throws Exception;

    void addRequest (String login, int idUser, String dates, Date dateOfSend) throws Exception;
    void addLesson (String login, String teacher, String theme, String date, String clientString) throws Exception;

    List<Lesson> getLessons() throws Exception;

    List<Request> getRequests() throws Exception;

    List<Complaint> getComplaints() throws Exception;

    List<Notification> getNotifications() throws Exception;

    List<ClientsOnLessons> getCOL() throws Exception;

    List<ClientsOnLessons> getCONByLesson(int idLesson) throws Exception;

    void updateLesson(String login, Lesson lesson) throws Exception;

    void updateCON(String login, ClientsOnLessons con) throws Exception;

    List<User> getUsers() throws Exception;

    List<User> getUsersByRole(String role) throws Exception;

    List<Notification> getNotificationsForUser(int id) throws Exception;

    void setNotificationStatus(int id, String status) throws SQLException;
}
