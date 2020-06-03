
package main.com.spbstu.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String user) {
        super("User " + user + " already exists");
    }
}

