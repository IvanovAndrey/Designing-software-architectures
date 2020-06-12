package main.com.spbstu.project;

import main.com.spbstu.exceptions.DBConnectionException;
import main.com.spbstu.exceptions.IncorrectPasswordException;
import main.com.spbstu.exceptions.NotAuthenticatedException;
import main.com.spbstu.storage.StorageRepository;
import main.com.spbstu.user.User;

public class Complaint {

    private int id;
    private int idIncedent;
    private String theme;
    private String text;



    public Complaint(int id_, int idIncedent_, String theme_, String text_) {
        id = id_;
        idIncedent = idIncedent_;
        theme = theme_;
        text = text_;
    }

    public Complaint(Complaint complaint) {
        id = complaint.id;
        idIncedent = complaint.idIncedent;
        theme = complaint.theme;
        text = complaint.text;

    }

    public int getIdIncedent() {
        return idIncedent;
    }

    public String getTheme() {
        return theme;
    }

    public String getText() {
        return text;
    }

    public void setId(int id_) {
        id = id_;
    }

    public int getId() { return id; }

    public Complaint getComplaint() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if ( (obj == null) ) return false;
        Complaint other = (Complaint) obj;
        boolean eq = true;
        if (id != other.id)
            eq = false;
        if (idIncedent !=other.idIncedent)
            eq = false;
        if (!(theme.equals(other.theme)))
            eq = false;
        if (!(text.equals(other.text)))
            eq = false;
        return eq;
    }

}
