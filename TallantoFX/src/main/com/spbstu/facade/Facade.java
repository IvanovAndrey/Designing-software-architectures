package main.com.spbstu.facade;

public interface Facade {
    void addUser(String login, String name, String role, String password) throws Exception;
    void authenticate(String login, String password) throws Exception;
    void signOut(String user) throws Exception;

}
