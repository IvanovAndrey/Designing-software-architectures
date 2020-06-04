package main.com.spbstu.facade;

import main.com.spbstu.project.Complaint;
import main.com.spbstu.user.User;

import java.sql.SQLException;

public interface Facade {
    void addUser(String login, String name, String role, String password) throws Exception;
    void authenticate(String login, String password) throws Exception;
    void signOut(String user) throws Exception;
    //String getUserName(String user) throws Exception;
    //String getUserStatus(String user) throws Exception;
    User getCurrentUser(String login) throws Exception;
    void addComplaint(int idIncedent, String theme, String text) throws Exception;
}
