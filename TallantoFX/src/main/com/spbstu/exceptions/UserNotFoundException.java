
package main.com.spbstu.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String user) {
        super("User " + user + " not found");
    }
}
