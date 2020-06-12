package main.com.spbstu.facade;

import main.com.spbstu.exceptions.DBConnectionException;
import main.com.spbstu.exceptions.UserNotFoundException;
import main.com.spbstu.project.*;
import main.com.spbstu.user.User;
import main.com.spbstu.storage.StorageRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

public class FacadeImpl implements Facade{
       private StorageRepository repository;
        private User currentUser;
         public FacadeImpl() {
             repository = new StorageRepository();
         }

    @Override
    public void addUser(String login, String name, String status, String password) throws Exception {
                 repository.addUser(login, name, status, password);
         }

    @Override
    public void authenticate(String login, String password) throws Exception {
                 repository.authenticateUser(login, password);
    }

    @Override
    public boolean isUserExist(String login) throws Exception {
        return repository.isUserExist(login);
    }

    @Override
    public String findLiginById(int id) throws Exception{
             return repository.findById(id).getLogin();
    }
    @Override
    public void signOut(String user) throws Exception {
                 User usr = repository.getUser(user);
                 usr.signOut();
    }

    @Override
    public User getCurrentUser(String login) throws Exception {
        return repository.getUser(login);
    }

    @Override
    public void addComplaint(String login, String idIncedent, String theme, String text) throws Exception{
            User user = repository.getUser(login);
            user.createComplaint(idIncedent,theme,text);
    }
    @Override
    public void addNotification(String login, int idFrom, int idTo,String status, String theme, String text) throws Exception{
        User user = new User(getCurrentUser(login));
        user.createNotification(idFrom, idTo, status, theme, text);
    }

    @Override
    public void addRequest(String login, int idUser, String dates, Date dateOfSend) throws Exception {
            User user = new User(getCurrentUser(login));
            user.createRequest(idUser,dates,dateOfSend);
    }


    @Override
    public void addLesson(String login, String teacher, String theme, String date, String clientString) throws Exception {
        User user = repository.getUser(login);
        user.createLesson(teacher, theme, date, clientString);
    }


    @Override
    public List<Lesson> getLessons() throws Exception {
        return repository.getLessons();
    }

    @Override
    public List<Request> getRequests() throws Exception {
        return repository.getRequests();
    }
    @Override
    public List<Complaint> getComplaints() throws Exception {
        return repository.getComplaints();
    }
    @Override
    public List<Notification> getNotifications() throws Exception {
        return repository.getNotifications();
    }
    @Override
    public List<ClientsOnLessons> getCOL() throws Exception {
        return repository.getCOL();
    }
    @Override
    public List<ClientsOnLessons> getCONByLesson(int idLesson) throws Exception {
             Lesson lesson = repository.findlessonById(idLesson);
        return lesson.getCol(lesson.getId());
    }
   @Override
   public void updateLesson(String login, Lesson lesson) throws Exception {
             User user = repository.getUser(login);
             user.updateLesson(lesson);
   }
    @Override
    public void updateCON(String login, ClientsOnLessons con) throws Exception {
        User user = repository.getUser(login);
        user.updateCON(con);
    }
    @Override
    public List<User> getUsers() throws Exception {
        return repository.getUsers();
    }
    @Override
    public List<User> getUsersByRole( String role) throws Exception {
        return repository.getUsersByRole(role);
    }
    @Override
    public List<Notification> getNotificationsForUser( int id) throws Exception {
        return repository.getNotificationsByIdTo(id);
    }

    @Override
    public void setNotificationStatus (int id, String status) throws SQLException {
             repository.setNotificationStatus(id, status);
    }
}
