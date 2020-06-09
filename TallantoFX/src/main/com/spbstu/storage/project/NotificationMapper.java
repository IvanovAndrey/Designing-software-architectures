package main.com.spbstu.storage.project;

import main.com.spbstu.project.Complaint;
import main.com.spbstu.project.Notification;
import main.com.spbstu.storage.DataGateway;
import main.com.spbstu.user.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NotificationMapper {
    private static Set<Notification> notifications = new HashSet<>();
    private Connection connection;

    public NotificationMapper() throws SQLException, IOException {

        DataGateway gateway = DataGateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public boolean addNotification(Notification notification) throws SQLException {
        String insertSQL = "INSERT INTO NOTIFICATIONS(id_from, id_to, status, theme, text) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement insertStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
        insertStatement.setInt(1, notification.getIdFrom());
        insertStatement.setInt(2, notification.getIdTo());
        insertStatement.setString(3, notification.getStatus());
        insertStatement.setString(4, notification.getTheme());
        insertStatement.setString(5, notification.getText());
        insertStatement.execute();
        ResultSet rs = insertStatement.getGeneratedKeys();
        if (rs.next()) {
            long id = rs.getLong(1);
            notification.setId((int) id);
        }
        return true;
    }


    private Notification findById(int id) throws SQLException {
        for (Notification it : notifications) {
            if (it.getId()==(id))
                return it;
        }

        String complaintsSelectStatement = "SELECT * FROM notifications WHERE id = ?;";
        PreparedStatement extractComplaintsStatement = connection.prepareStatement(complaintsSelectStatement);
        extractComplaintsStatement.setInt(1, id);
        ResultSet rs = extractComplaintsStatement.executeQuery();

        if (!rs.next()) return null;
        int idFrom = rs.getInt("id_from");
        int idTo = rs.getInt("id_to");
        String status = rs.getString("status");
        String theme = rs.getString("theme");
        String text = rs.getString("text");

        Notification newNotification = new Notification(id, idFrom, idTo, status, theme, text);
        notifications.add(newNotification);

        return newNotification;
    }



    public List<Notification> findAll() throws SQLException {
        List<Notification> all = new ArrayList<>();

        String complaintSelectStatement = "SELECT id FROM notifications;";
        Statement extractComplaintStatement = connection.createStatement();
        ResultSet rs = extractComplaintStatement.executeQuery(complaintSelectStatement);

        while (rs.next()) {
            all.add(findById(rs.getInt("id")));
        }

        return all;
    }

    public List<Notification> findAllByIdTo(int id) throws SQLException {
        List<Notification> all = new ArrayList<>();

        String userSelectStatement = "SELECT id FROM NOTIFICATIONS WHERE id_to = ?;";
        PreparedStatement extractUserStatement = connection.prepareStatement(userSelectStatement);
        extractUserStatement.setInt(1, id);
        ResultSet rs = extractUserStatement.executeQuery();

        while (rs.next()) {
            all.add(findById(rs.getInt("id")));
        }
        return all;
    }

    public void setStatus (int id, String status) throws SQLException{
        String updateSQL = "UPDATE notifications SET status = ? WHERE id = ?;";
        PreparedStatement updateStatus = connection.prepareStatement(updateSQL);
        updateStatus.setString(1, status);
        updateStatus.setInt(2, id);
        updateStatus.execute();
    }

}
