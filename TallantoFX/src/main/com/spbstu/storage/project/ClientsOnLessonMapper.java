package main.com.spbstu.storage.project;

import main.com.spbstu.project.ClientsOnLessons;
import main.com.spbstu.project.Lesson;
import main.com.spbstu.project.Notification;
import main.com.spbstu.storage.DataGateway;
import main.com.spbstu.storage.Mapper;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClientsOnLessonMapper implements Mapper <ClientsOnLessons> {
    private static Set<ClientsOnLessons> clientsOnLessons = new HashSet<>();
    private Connection connection;

    public ClientsOnLessonMapper() throws SQLException, IOException {
        DataGateway gateway = DataGateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public boolean addClientsOnLesson(int idLesson, int idClient) throws SQLException {
        String insertSQL = "INSERT INTO CLIENTS_ON_LESSONS(id_client, id_lesson, status, commentary) VALUES (?, ?, ?,?);";
        PreparedStatement insertStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
        insertStatement.setInt(1, idClient);
        insertStatement.setInt(2, idLesson);
        insertStatement.setString(3, "Планирует посетить");
        insertStatement.setString(4, "");
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
    public ClientsOnLessons findById (int id) throws SQLException {
        for (ClientsOnLessons it : clientsOnLessons) {
            if (it.getId()==(id))
                return it;
        }

        String complaintsSelectStatement = "SELECT * FROM clients_on_lessons WHERE id = ?;";
        PreparedStatement extractLessonsStatement = connection.prepareStatement(complaintsSelectStatement);
        extractLessonsStatement.setInt(1, id);
        ResultSet rs = extractLessonsStatement.executeQuery();

        if (!rs.next()) return null;
        int idClient = rs.getInt("id_client");
        int idLesson = rs.getInt("id_lesson");
        String status = rs.getString("status");
        String commentary = rs.getString("commentary");
        ClientsOnLessons newCOL = new ClientsOnLessons(id, idClient, idLesson, status, commentary);
        clientsOnLessons.add(newCOL);

        return newCOL;
    }

    public List<ClientsOnLessons> findAll() throws SQLException {
        List<ClientsOnLessons> all = new ArrayList<>();

        String lessonSelectStatement = "SELECT id FROM clients_on_lessons;";
        Statement extractLessonStatement = connection.createStatement();
        ResultSet rs = extractLessonStatement.executeQuery(lessonSelectStatement);

        while (rs.next()) {
            all.add(findById(rs.getInt("id")));
        }

        return all;
    }
    public List<ClientsOnLessons> findOnLesson(int idLesson) throws SQLException {
        List<ClientsOnLessons> all = new ArrayList<>();

        String lessonSelectStatement = "SELECT id FROM clients_on_lessons WHERE id_lesson = ?;";
        PreparedStatement extractLessonStatement = connection.prepareStatement(lessonSelectStatement);
        extractLessonStatement.setInt(1, idLesson);
        ResultSet rs = extractLessonStatement.executeQuery();

        while (rs.next()) {
            all.add(findById(rs.getInt("id")));
        }

        return all;
    }


    public void update(ClientsOnLessons clientOnLesson) throws SQLException{
        if(!(clientsOnLessons.contains(clientOnLesson)))
        {
            addClientsOnLesson(clientOnLesson.getIdLesson(),clientOnLesson.getIdClient());
            clientsOnLessons.add(clientOnLesson);
        }
        String updateSQL = "UPDATE clients_on_lessons set status = ?, commentary = ?  WHERE id = ?;";
        PreparedStatement updateStatus = connection.prepareStatement(updateSQL);
        updateStatus.setString(1, clientOnLesson.getCommentary());
        updateStatus.setString(2, clientOnLesson.getStatus());
        updateStatus.setInt(3, clientOnLesson.getId());
        updateStatus.execute();
    }

    @Override
    public void update() throws SQLException {
        for (ClientsOnLessons it : clientsOnLessons)
            update(it);
    }

    @Override
    public void clear() {
        clientsOnLessons.clear();
    }

    public boolean isOnLesson (int idUser, int idIncedent) throws SQLException {
        String lessonSelectStatement = "SELECT id_client FROM clients_on_lessons WHERE id = ?";
        PreparedStatement extractLessonStatement = connection.prepareStatement(lessonSelectStatement);
        extractLessonStatement.setInt(1, idIncedent);
        ResultSet rs = extractLessonStatement.executeQuery();

        if (!rs.next()) return false;
        int idRes = rs.getInt("id_client");
        if(idRes == idUser)
        return true;
        else return false;
    }
}
