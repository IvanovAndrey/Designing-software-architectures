package main.com.spbstu.project;

import java.sql.Date;

public class Request {
    private int id;
    private int idUser;
    private String status;
    private String dates;
    private java.sql.Date dateOfSend;



    public Request(int id_, int idUser_, String status_, String dates_, Date dateOfSend_) {
        id = id_;
        idUser = idUser_;
        status = status_;
        dates = dates_;
        dateOfSend = dateOfSend_;
    }

    public Request(Request request) {
        id = request.id;
        idUser = request.idUser;
        status = request.status;
        dates = request.dates;
        dateOfSend =request.dateOfSend;

    }

    public int getIdUser() {
        return idUser;
    }

    public String getStatus() {
        return status;
    }

    public String getDates() {
        return dates;
    }

    public java.sql.Date getDateOfSend() {
        return dateOfSend;
    }

    public void setId(int id_) {
        id = id_;
    }

    public int getId() { return id; }

    public Request getRequest() {
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if ( (obj == null) ) return false;
       Request other = (Request) obj;
        boolean eq = true;
        if (id != other.id)
            eq = false;
        if (idUser !=other.idUser)
            eq = false;
        if (!(status.equals(other.status)))
            eq = false;
        if (!(dates.equals(other.dates)))
            eq = false;
        if (!(dateOfSend.equals(other.dateOfSend)))
            eq = false;
        return eq;
    }
}
