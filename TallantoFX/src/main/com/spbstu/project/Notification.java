package main.com.spbstu.project;

public class Notification {
    private int id;
    private int idFrom;
    private int idTo;
    private String status;
    private String theme;
    private String text;



    public Notification(int id_, int idFrom_, int idTo_,String status_, String theme_, String text_) {
        id = id_;
        idFrom = idFrom_;
        idTo = idTo_;
        status = status_;
        theme = theme_;
        text = text_;
    }

    public Notification(Notification notification) {
        id = notification.id;
        idFrom = notification.idFrom;
        idTo = notification.idTo;
        status = notification.status;
        theme = notification.theme;
        text = notification.text;

    }

    public int getIdFrom() {
        return idFrom;
    }

    public int getIdTo() {
        return idTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status_) {
        status = status_;
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

    public Notification getNotification() {
        return this;
    }
    @Override
    public boolean equals(Object obj) {
        if ( (obj == null) ) return false;
        Notification other = (Notification) obj;
        boolean eq = true;
        if (id != other.id)
            eq = false;
        if (idFrom !=other.idFrom)
            eq = false;
        if (idTo != other.idTo)
            eq = false;
        if (!(status.equals(other.status)))
            eq = false;
        if (!(theme.equals(other.theme)))
            eq = false;
        if (!(text.equals(other.text)))
            eq = false;
        return eq;
    }
}
