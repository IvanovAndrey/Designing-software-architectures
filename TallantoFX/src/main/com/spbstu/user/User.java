

package main.com.spbstu.user;


import main.com.spbstu.exceptions.*;
import main.com.spbstu.project.*;
import main.com.spbstu.storage.StorageRepository;

import javax.jws.soap.SOAPBinding;
import java.sql.Date;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class User implements UserInterface {

    private int id;
    private String login;
    private String name;
    private String status;
    private boolean authenticated;
    StorageRepository repository = new StorageRepository();

    public User(int id_, String login_, String name_, String status_) {
        id = id_;
        login = login_;
        name = name_;
        status = status_;
        authenticated = false;
    }

    public User(User user) {
        id = user.id;
        login = user.login;
        name = user.name;
        status = user.status;
        authenticated = user.authenticated;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void signIn(String password) throws DBConnectionException, IncorrectPasswordException {
        (new StorageRepository()).authenticateUser(login, password);
        authenticated = true;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void signOut() {
        authenticated = false;
    }

    public String toString() {
        return  login + ":" + name + "<" + status + ">";
    }

    @Override
    public void setId(int id_) {
        id = id_;
    }

    @Override
    public int getId() { return id; }

    @Override
    public User getUser() {
        return this;
    }

    @Override
    public void checkAuthenticated() throws NotAuthenticatedException {
        if (isAuthenticated()) return;
        throw new NotAuthenticatedException(toString() + " is not authenticated");
    }
    @Override
    public Notification createNotification(int idFrom, int idTo, String status, String theme, String text) throws DBConnectionException {
        Notification notification = new Notification(0, idFrom, idTo, status, theme, text);
        notification.setId(repository.addNotification(idFrom, idTo, status, theme, text));
        return notification;
    }

    @Override
    public Notification findNotificationById(int id) throws SQLException {
        return  repository.findNotificationById(id);
    }

    @Override
    public Request createRequest(int idUser, String dates, Date dateOfSend) throws DBConnectionException {
        Request request = new Request(0, idUser, "NEW", dates, dateOfSend);
        request.setId(repository.addRequest(idUser,  dates, dateOfSend));
        return request;
    }

    @Override
    public void setNotificationStatus(int id, String status) throws SQLException {
        repository.setNotificationStatus(id, status);
    }

    @Override
    public Date dateConversion(String date){
        LocalDate currentDate = LocalDate.now();
        LocalDate nextDate = currentDate;
        if(date.equals("ПН"))
            nextDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        if(date.equals("ВТ"))
            nextDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        if(date.equals("СР"))
            nextDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
        if(date.equals("ЧТ"))
            nextDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        if(date.equals("ПТ"))
            nextDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        if(date.equals("СБ"))
            nextDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        if(date.equals("ВС"))
            nextDate = currentDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        Date sqlDate;
        return sqlDate = java.sql.Date.valueOf(nextDate);
    }

    @Override
    public void addClientOnLesson(String teacher, String date, String client) throws Exception {
        User userClient = repository.getUser(client);
        User userTeacher = repository.getUser(teacher);
        Date sqlDate = dateConversion(date);
        int id = repository.findIDLesson(userTeacher.getId(), sqlDate);
        repository.addClientOnLesson(id, userClient.getId());
    }

    @Override
    public Lesson createLesson(String teacher, String theme, String date, String clientString) throws Exception {
        String[] clients;
        String delimetr ="-";
        clients = clientString.replace(" ", "").split(delimetr);
        if (theme.isEmpty())
            throw new EmptyFieldException("theme");
        if(teacher.isEmpty())
            throw new EmptyFieldException("teacher");
        if(clientString.isEmpty() )
            throw new EmptyFieldException("clients");
        /*if (clients.length > 5)
            throw new EmptyFieldException("teacher");*/
        if(!(repository.isUserExist(teacher)))
            throw new UserNotFoundException(teacher);
        for (int i = 0; i <clients.length; i++) {
            if (!(repository.isUserExist(clients[i])))
                throw new UserNotFoundException(clients[i]);
            else if (!(repository.getUser(clients[i]).getStatus().equals("client")))
                throw new StatusExeption("client");
        }
        User user = repository.getUser(teacher);
        if (!(user.getStatus().equals("teacher")))
            throw new StatusExeption("teacher");

        Date sqlDate = dateConversion(date);
        if((repository.isLessonExist(user.getId(), sqlDate))) {
                throw new LessonIsAlreadyExistException();
        }
                Lesson lesson = new Lesson(0, user.getId() ,theme,"","OPEN", sqlDate);
                lesson.setId(repository.addLesson(user.getId(),theme,sqlDate));

        for (int i = 0; i <clients.length; i++) {
            user.addClientOnLesson(teacher, date, clients[i]);
        }
            return lesson;
    }

    @Override
    public boolean equals(Object obj) {
        if ( (obj == null) ) return false;
        User other = (User)obj;
        return login.equals(other.login);
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }

    @Override
    public void updateLesson(Lesson lesson) throws Exception {
        repository.updateLesson(lesson);
    }
    @Override
    public void updateCON(ClientsOnLessons con) throws Exception {
        repository.updateCON(con);
    }

    public boolean isOnLesson(int idUser, int idIncedent) throws SQLException {
        return repository.isOnLesson(idUser, idIncedent);
    }

    @Override
    public Complaint createComplaint(String idIncedent, String theme, String text) throws Exception{
        if (idIncedent.isEmpty())
            throw new EmptyFieldException("id incedent");
        if(theme.isEmpty())
            throw new EmptyFieldException("theame");
        if(text.isEmpty() )
            throw new EmptyFieldException("text");
        int idI = Integer.parseInt(idIncedent);
        User user = getUser();
        if(!(isOnLesson(user.getId(), idI)))
            throw new IncorrectIdIncedentException();

        Complaint complaint = new Complaint(0, idI, theme, text);
        complaint.setId(repository.addComplaint(idI,theme,text));
        return complaint;
    }
}
