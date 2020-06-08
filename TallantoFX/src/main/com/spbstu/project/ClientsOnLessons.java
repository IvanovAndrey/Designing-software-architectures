package main.com.spbstu.project;

import java.sql.Date;

public class ClientsOnLessons {

    private int id;
    private int idClient;
    private int idLesson;
    private String status;
    private String commentary;


    public ClientsOnLessons(int id_, int idClient_, int idLesson_, String status_, String commentary_) {
        id = id_;
        idClient = idClient_;
        idLesson = idLesson_;
        status = status_;
        commentary = commentary_;
    }

    public ClientsOnLessons(ClientsOnLessons clientsOnLessons) {
        id = clientsOnLessons.id;
        idClient = clientsOnLessons.idClient;
        idLesson = clientsOnLessons.idLesson;
        commentary = clientsOnLessons.commentary;
        status = clientsOnLessons.status;
    }

    public int getIdClient() {
        return idClient;
    }

    public int getIdLesson() {
        return idLesson;
    }

    public String getStatus() {
        return status;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setId(int id_) {
        id = id_;
    }

    public int getId() { return id; }

    public ClientsOnLessons getClientsOnLessons() {
        return this;
    }

    public void setStatus (String status_) {status = status_;
    }

    public void setCommetnary(String commentary_) {
        commentary = commentary_;
    }

}
