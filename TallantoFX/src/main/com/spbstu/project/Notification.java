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
}
