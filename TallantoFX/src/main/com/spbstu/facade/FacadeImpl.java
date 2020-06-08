package main.com.spbstu.facade;

import main.com.spbstu.exceptions.DBConnectionException;
import main.com.spbstu.exceptions.UserNotFoundException;
import main.com.spbstu.project.ClientsOnLessons;
import main.com.spbstu.project.Complaint;
import main.com.spbstu.project.Lesson;
import main.com.spbstu.project.Request;
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
    public void addComplaint(int idIncedent, String theme, String text) throws Exception{
            repository.addComplaint(idIncedent,theme,text);
    }

    @Override
    public void addRequest(int idUser, String dates, Date dateOfSend) throws Exception {
            repository.addRequest(idUser,dates,dateOfSend);
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
    public boolean addLesson(String teacher, String theme, String date) throws Exception {
        User user = repository.getUser(teacher);
        if (user.getStatus().equals("teacher")){
        Date sqlDate = dateConversion(date);
            if(!(repository.isLessonExist(user.getId(), sqlDate))){
            repository.addLesson(user.getId(),theme,sqlDate);
            return true;} else { return false;}}
        else
            return false;
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
    public List<ClientsOnLessons> getCOL() throws Exception {
        return repository.getCOL();
    }
   @Override
   public void updateLesson(Lesson lesson) throws Exception {
             repository.updateLesson(lesson);
   }
    @Override
    public void updateCON(ClientsOnLessons con) throws Exception {
        repository.updateCON(con);
    }


}
