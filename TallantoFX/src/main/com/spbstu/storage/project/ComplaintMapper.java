package main.com.spbstu.storage.project;

import main.com.spbstu.exceptions.EndBeforeStartException;
import main.com.spbstu.project.Complaint;
import main.com.spbstu.project.Lesson;
import main.com.spbstu.storage.DataGateway;
import main.com.spbstu.storage.Mapper;
import main.com.spbstu.user.User;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ComplaintMapper implements Mapper<Complaint> {
    private static Set<Complaint> complaints = new HashSet<>();
    private Connection connection;

    public ComplaintMapper() throws SQLException, IOException {

        DataGateway gateway = DataGateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public int addComplaint(Complaint complaint) throws SQLException {
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
        return complaint.getId();
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

    @Override
    public Complaint findById(int id) throws SQLException, EndBeforeStartException {
        for (Complaint it : complaints) {
            if (it.getId()==(id))
                return it;
        }

        String complaintsSelectStatement = "SELECT * FROM COMPLAINTS WHERE id = ?;";
        PreparedStatement extractComplaintsStatement = connection.prepareStatement(complaintsSelectStatement);
        extractComplaintsStatement.setInt(1, id);
        ResultSet rs = extractComplaintsStatement.executeQuery();

        if (!rs.next()) return null;
        int idIncedent = rs.getInt("id_incedent");
        String theme = rs.getString("theme");
        String text = rs.getString("text");

        Complaint newComplaint = new Complaint(id, idIncedent, theme, text);
        complaints.add(newComplaint);

        return newComplaint;
    }

    public List<Complaint> findAll() throws SQLException, EndBeforeStartException {
        List<Complaint> all = new ArrayList<>();

        String complaintSelectStatement = "SELECT id FROM complaints;";
        Statement extractComplaintStatement = connection.createStatement();
        ResultSet rs = extractComplaintStatement.executeQuery(complaintSelectStatement);

        while (rs.next()) {
            all.add(findById(rs.getInt("id")));
        }

        return all;
    }

    @Override
    public void update(Complaint complaint) throws SQLException {
        if (!(complaints.contains(complaint))){
            addComplaint(complaint);
            complaints.add(complaint);
        }else{
            String insertSQL = "UPDATE COMPLAINTS SET id_incedent= ?, theme= ?, text =? WHERE id =?;";
            PreparedStatement insertStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, complaint.getIdIncedent());
            insertStatement.setString(2, complaint.getTheme());
            insertStatement.setString(3, complaint.getText());
            insertStatement.setInt(4, complaint.getId());
            insertStatement.execute();
            ResultSet rs = insertStatement.getGeneratedKeys();
        }
    }

    @Override
    public void update() throws SQLException {
        for (Complaint it : complaints)
        update(it);
    }

    @Override
    public void clear() {
        complaints.clear();
    }


}
