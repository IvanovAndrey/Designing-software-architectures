

package main.com.spbstu.exceptions;

public class DBConnectionException extends Exception {
    public DBConnectionException() {
        super("Could not connect to database");
    }
}
