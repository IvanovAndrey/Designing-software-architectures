package main.com.spbstu.exceptions;

public class StatusExeption extends Exception {
    public StatusExeption(String status) {
        super("Error\n The user has the following status  " + status + "!");
    }
}
