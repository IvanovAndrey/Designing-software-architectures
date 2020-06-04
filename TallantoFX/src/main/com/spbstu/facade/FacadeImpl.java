package main.com.spbstu.facade;

import main.com.spbstu.exceptions.DBConnectionException;
import main.com.spbstu.exceptions.UserNotFoundException;
import main.com.spbstu.project.Complaint;
import main.com.spbstu.user.User;
import main.com.spbstu.storage.StorageRepository;

import java.sql.SQLException;

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


   /* @Override
    public String getUserName(String user) throws Exception {
        return repository.getUser(user).getName();
    }

    @Override
    public String getUserStatus(String user) throws Exception {
        return repository.getUser(user).getStatus();
    }*/


}
