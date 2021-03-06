package main.com.spbstu.project;

import main.com.spbstu.storage.StorageRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class Lesson {

    private int id;
    private int idTeacher;
    private String theme;
    private String commentary;
    private String status;
    private Date dateOfLesson;
    StorageRepository repository = new StorageRepository();

    public Lesson(int id_, int idTeacher_, String theme_, String commentary_, String status_, Date dateOfLesson_) {
        id = id_;
        idTeacher = idTeacher_;
        theme = theme_;
        commentary = commentary_;
        status = status_;
        dateOfLesson = dateOfLesson_;
    }

    public Lesson(Lesson lesson) {
        id = lesson.id;
        idTeacher = lesson.idTeacher;
        theme = lesson.theme;
        commentary = lesson.commentary;
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

    public String getCommentary() {
        return commentary;
    }

    public Date getDateOfLesson() {return dateOfLesson;}

    public void setId(int id_) {
        id = id_;
    }

    public int getId() { return id; }

    public Lesson getLesson() {
        return this;
    }

    public void setStatus (String status_) {status = status_;
    }

    public void setCommetnary(String commentary_) {
        commentary = commentary_;
    }

    public void setTheme(String theme_) {
        theme = theme_;
    }

    public List<ClientsOnLessons> getCol (int idLesson) throws SQLException {
        return repository.getCONByLesson(idLesson);
    }

    @Override
    public boolean equals(Object obj) {
        if ( (obj == null) ) return false;
        Lesson other = (Lesson) obj;
        boolean eq = true;
        if (id != other.id)
            eq = false;
        if (idTeacher !=other.idTeacher)
            eq = false;
        if (!theme.equals(other.theme))
            eq = false;
        if (!(status.equals(other.status)))
            eq = false;
        if (!(commentary.equals(other.commentary)))
            eq = false;
        if (!(dateOfLesson.equals(other.dateOfLesson)))
            eq = false;
        return eq;
    }
}
