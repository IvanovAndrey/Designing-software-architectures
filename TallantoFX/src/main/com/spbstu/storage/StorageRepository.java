package main.com.spbstu.storage;

import main.com.spbstu.exceptions.DBConnectionException;
import main.com.spbstu.exceptions.IncorrectPasswordException;
import main.com.spbstu.exceptions.UserAlreadyExistsException;
import main.com.spbstu.exceptions.UserNotFoundException;
import main.com.spbstu.project.*;
import main.com.spbstu.storage.project.*;
import main.com.spbstu.storage.user.UserMapper;
import main.com.spbstu.user.User;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class StorageRepository {

    private static UserMapper userMapper;
    private static ComplaintMapper complaintMapper;
    private static RequestMapper requestMapper;
    private static LessonMapper lessonMapper;
    private static ClientsOnLessonMapper clientsOnLessonMapper;
    private static NotificationMapper notificationMapper;

    public StorageRepository() {
        try {
            if (userMapper == null) userMapper = new UserMapper();
            if (complaintMapper == null) complaintMapper = new ComplaintMapper();
            if (requestMapper == null) requestMapper = new RequestMapper();
            if (lessonMapper == null) lessonMapper = new LessonMapper();
            if (clientsOnLessonMapper == null) clientsOnLessonMapper = new ClientsOnLessonMapper();
            if (notificationMapper == null) notificationMapper = new NotificationMapper();

        } catch (
                SQLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String login, String name, String status, String password)
            throws UserAlreadyExistsException, DBConnectionException {
        try {
            if (userMapper.findByLogin(login) != null)
                throw new UserAlreadyExistsException(login);

            User newUser = new User(0, login, name, status);
            userMapper.addUser(newUser, password);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public User getUser(String login) throws UserNotFoundException, DBConnectionException {
        try {
            User user = userMapper.findByLogin(login);
            if (user == null) throw new UserNotFoundException(login);
            return user;
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public User authenticateUser(String login, String password) throws DBConnectionException, IncorrectPasswordException {
        try {
            User user = getUser(login);
            if (!userMapper.authenticateUser(user, password))
                throw new IncorrectPasswordException();
            else
                return user;
        } catch (UserNotFoundException | SQLException e) {
            throw new DBConnectionException();
        }
    }

    public User findById (int id) throws DBConnectionException {
        try {
            User user = userMapper.findByID(id);
            if (user == null) throw new UserNotFoundException(user.getLogin());
            return user;
        } catch (SQLException | UserNotFoundException e) {
            throw new DBConnectionException();
        }
    }
    public boolean isUserExist(String login) throws DBConnectionException {
        try {
            if (!(userMapper.findByLogin(login) == null))
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addComplaint(int idIncedent, String theme, String text)
            throws DBConnectionException {
        try {
            Complaint complaint = new Complaint(0, idIncedent, theme, text);
            complaintMapper.addComplaint(complaint);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public Complaint getComplaint(int idIncedent) throws DBConnectionException {
        try {
            return complaintMapper.findByIdIncedent(idIncedent);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public void addRequest(int idUser, String datas, Date dateOdSend)
            throws DBConnectionException {
        try {
            if (!(requestMapper.findByIdUser(idUser) == null)) {
                Request request = new Request(0, idUser, "NEW", datas, dateOdSend);
                requestMapper.updateRequest(request);
            } else {
                Request request = new Request(0, idUser, "NEW", datas, dateOdSend);
                requestMapper.addRequest(request);
            }
        } catch (SQLException e) {
            throw new DBConnectionException();
        }

    }

    public void addLesson(int idTeacher, String theme, Date dateOfLesson) throws DBConnectionException {
        try {
            Lesson lesson = new Lesson(0, idTeacher, theme, "", "OPEN", dateOfLesson);
            lessonMapper.addLesson(lesson);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public void addClientOnLesson(int idLesson, int idClient) throws DBConnectionException {
        try {
            clientsOnLessonMapper.addClientsOnLesson(idLesson, idClient);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }
    public void addNotification(int idFrom, int idTo,String status, String theme, String text) throws DBConnectionException {
        try {
            Notification notification = new Notification(0, idFrom, idTo, status, theme, text);
            notificationMapper.addNotification(notification);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public Lesson findlessonById(int id) throws DBConnectionException {
        try {
            return lessonMapper.findById(id);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public int findIDLesson(int idTeacher, java.sql.Date date) throws DBConnectionException {
        try {
            return lessonMapper.findIDLesson(idTeacher, date);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public boolean isLessonExist(int idTeacher, java.sql.Date date) throws DBConnectionException {
        try {
            if(lessonMapper.findIDLesson(idTeacher, date) == null)
            return false;
            else
                return true;
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public void updateLesson (Lesson lesson) throws Exception{
        lessonMapper.update(lesson);
    }
    public void updateCON (ClientsOnLessons con) throws Exception{
        clientsOnLessonMapper.update(con);
    }

    public List<Lesson> getLessons() throws SQLException {
        return lessonMapper.findAll();
    }

    public List<Request> getRequests() throws SQLException {
        return requestMapper.findAll();
    }

    public List<Complaint> getComplaints() throws SQLException {
        return complaintMapper.findAll();
    }

    public List<ClientsOnLessons> getCOL() throws SQLException {
        return clientsOnLessonMapper.findAll();
    }
    public List<Notification> getNotifications() throws SQLException {
        return notificationMapper.findAll();
    }
    public List<User> getUsers() throws SQLException {
        return userMapper.findAll();
    }
    public List<User> getUsersByRole(String role) throws SQLException {
        return userMapper.findRole(role);
    }
    public List<Notification> getNotificationsByIdTo(int id) throws SQLException {
        return notificationMapper.findAllByIdTo(id);
    }

    public void setNotificationStatus(int id, String status) throws SQLException {
        notificationMapper.setStatus(id,status);
    }
}


