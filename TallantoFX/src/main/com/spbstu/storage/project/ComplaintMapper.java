package main.com.spbstu.storage.project;

import main.com.spbstu.exceptions.EndBeforeStartException;
import main.com.spbstu.project.Complaint;
import main.com.spbstu.storage.DataGateway;
import main.com.spbstu.user.User;

import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComplaintMapper {
    private static Set<Complaint> complaints = new HashSet<>();
    private Connection connection;

    public ComplaintMapper() throws SQLException, IOException {

        DataGateway gateway = DataGateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public boolean addComplaint(Complaint complaint) throws SQLException {
        String insertSQL = "INSERT INTO COMPLAINTS(id_incedent, theme, text) VALUES (?, ?, ?);";
        PreparedStatement insertStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
        insertStatement.setInt(1, complaint.getIdIncedent());
        insertStatement.setString(2, complaint.getTheme());
        insertStatement.setString(3, complaint.getText());
        insertStatement.execute();
        ResultSet rs = insertStatement.getGeneratedKeys();
        if (rs.next()) {
            long id = rs.getLong(1);
            complaint.setId((int) id);
        }
        return true;
    }

    public Complaint findByIdIncedent(int idIncedent) throws SQLException {
        for (Complaint it : complaints) {
            if (it.getIdIncedent()==(idIncedent))
                return it;
        }

        // User not found, extract from database
        String complaintsSelectStatement = "SELECT * FROM COMPLAINTS WHERE id_incedent = ?;";
        PreparedStatement extractComplaintsStatement = connection.prepareStatement(complaintsSelectStatement);
        extractComplaintsStatement.setInt(1, idIncedent);
        ResultSet rs = extractComplaintsStatement.executeQuery();

        if (!rs.next()) return null;
        int id = rs.getInt("id");
        String theme = rs.getString("theme");
        String text = rs.getString("text");

        Complaint newComplaint = new Complaint(id, idIncedent, theme, text);
        complaints.add(newComplaint);

        return newComplaint;
    }

}
