

package main.com.spbstu.user;


import main.com.spbstu.exceptions.NotAuthenticatedException;
import main.com.spbstu.exceptions.DBConnectionException;
import main.com.spbstu.exceptions.IncorrectPasswordException;
import main.com.spbstu.storage.StorageRepository;

public class User implements UserInterface {

    private int id;
    private String login;
    private String name;
    private String status;
    private boolean authenticated;


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
    public boolean equals(Object obj) {
        if ( (obj == null) ) return false;
        User other = (User)obj;
        return login.equals(other.login);
    }

    @Override
    public int hashCode() {
        return login.hashCode();
    }
}