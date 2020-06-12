package main.com.spbstu.exceptions;

public class IncorrectIdIncedentException extends Exception {
    public IncorrectIdIncedentException() {
        super("This user is not registered for the specified lesson ");
    }
}
