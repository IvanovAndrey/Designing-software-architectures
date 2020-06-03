package main.com.spbstu.facade;

import main.com.spbstu.user.User;
import main.com.spbstu.storage.StorageRepository;

public class FacadeImpl implements Facade{
       private StorageRepository repository;

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
}
