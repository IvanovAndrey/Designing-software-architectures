package main.com.spbstu.project;

import java.sql.Date;

public class Lesson {

    private int id;
    private int idTeacher;
    private String theme;
    private String commentary;
    private String status;
    private Date dateOfLesson;



    public Lesson(int id_, int idTeacher_, String theme_, String commentary_, String status_, Date dateOfLesson_) {
        idTeacher = idTeacher_;
        theme =theme_;
        commentary = commentary_;
        status = status_;
        dateOfLesson = dateOfLesson_;
    }

    public Lesson(Lesson lesson) {
        id = lesson.id;
        idTeacher = lesson.idTeacher;
        theme = lesson.theme;
        commentary =lesson.commentary;
        status = lesson.status;
        dateOfLesson = lesson.dateOfLesson;
    }

    public int getIdTeacher() {
        return idTeacher;
    }

    public String getTheme() {
        return theme;
    }

    public String getStatus() {
        return status;
    }

    public Date getDateOfLesson() {return dateOfLesson;}

    public void setId(int id_) {
        id = id_;
    }

    public int getId() { return id; }

    public Lesson getLesson() {
        return this;
    }


}
