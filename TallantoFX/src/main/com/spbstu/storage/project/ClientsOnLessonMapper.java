package main.com.spbstu.storage.project;

import main.com.spbstu.project.Lesson;
import main.com.spbstu.storage.DataGateway;

import java.io.IOException;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class ClientsOnLessonMapper {
    private static Set<Lesson> lessons = new HashSet<>();
    private Connection connection;

    public ClientsOnLessonMapper() throws SQLException, IOException {
        DataGateway gateway = DataGateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public boolean addClientsOnLesson(int idLesson, int idClient) throws SQLException {
        String insertSQL = "INSERT INTO CLIENTS_ON_LESSONS(id_client, id_lesson, commentary) VALUES (?, ?, ?);";
        PreparedStatement insertStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
        insertStatement.setInt(1, idClient);
        insertStatement.setInt(2, idLesson);
        insertStatement.setString(3, "");
        insertStatement.execute();
        return true;
    }

    public int getId (int idLesson, int idClient) throws SQLException {
        String userSelectStatement = "SELECT id FROM CLIENTS_ON_LESSONS WHERE id_lesson = ? AND id_client = ?;";
        PreparedStatement extractUserStatement = connection.prepareStatement(userSelectStatement);
        extractUserStatement.setInt(1, idLesson);
        extractUserStatement.setInt(2, idClient);
        ResultSet rs = extractUserStatement.executeQuery();
        if (!rs.next()) return -1;
        return rs.getInt("id");
    }


    public void setCommentary (int id, String commentary) throws SQLException{
        String updateSQL = "UPDATE CLIENTS_ON_LESSONS SET commentary = ? WHERE id = ?;";
        PreparedStatement updateStatus = connection.prepareStatement(updateSQL);
        updateStatus.setString(1, commentary);
        updateStatus.setInt(2, id);
        updateStatus.execute();
    }

    public String getCommentary (int id) throws SQLException{
        String userSelectStatement = "SELECT commentary FROM CLIENTS_ON_LESSONS WHERE id = ?;";
        PreparedStatement extractUserStatement = connection.prepareStatement(userSelectStatement);
        extractUserStatement.setInt(1, id);
        ResultSet rs = extractUserStatement.executeQuery();
        return rs.getString("commentary");
    }
}
