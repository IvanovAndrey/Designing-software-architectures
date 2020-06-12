package main.com.spbstu.exceptions;

public class EmptyFieldException extends Exception {
    public EmptyFieldException(String field) {
        super("Error\n Field " + field + " is empty!");
    }
}
