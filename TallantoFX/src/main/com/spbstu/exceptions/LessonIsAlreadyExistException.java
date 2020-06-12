package main.com.spbstu.exceptions;

public class LessonIsAlreadyExistException extends Exception  {
    public LessonIsAlreadyExistException() {
        super("Error\n Lesson on this date is already exist!");
    }
}
