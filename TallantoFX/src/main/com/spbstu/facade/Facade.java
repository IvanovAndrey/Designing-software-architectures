package main.com.spbstu.facade;

import main.com.spbstu.project.Complaint;
import main.com.spbstu.project.Lesson;
import main.com.spbstu.project.Request;
import main.com.spbstu.user.User;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public interface Facade {
    void addUser(String login, String name, String role, String password) throws Exception;
    void authenticate(String login, String password) throws Exception;
    boolean isUserExist (String login) throws Exception;
    void signOut(String user) throws Exception;
    User getCurrentUser(String login) throws Exception;
    void addComplaint(int idIncedent, String theme, String text) throws Exception;
    void addRequest (int idUser, String dates, Date dateOfSend) throws Exception;
    Date dateConversion(String date);
    boolean addLesson (String teacher, String theme, String date) throws Exception;
    void addClientOnLesson(String teacher, String date, String client) throws Exception;

    List<Lesson> getLessons() throws Exception;

    List<Request> getRequests() throws Exception;
}
