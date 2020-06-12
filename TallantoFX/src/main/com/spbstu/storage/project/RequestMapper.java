package main.com.spbstu.storage.project;

import main.com.spbstu.project.Complaint;
import main.com.spbstu.project.Lesson;
import main.com.spbstu.project.Notification;
import main.com.spbstu.project.Request;
import main.com.spbstu.storage.DataGateway;
import main.com.spbstu.storage.Mapper;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RequestMapper implements Mapper<Request> {
    private static Set<Request> requests = new HashSet<>();
    private Connection connection;

    public RequestMapper() throws SQLException, IOException {

        DataGateway gateway = DataGateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public Request findByIdUser(int idUser) throws SQLException {
        for (Request it : requests) {
            if (it.getIdUser()==(idUser))
                return it;
        }

        String requestsSelectStatement = "SELECT * FROM REQUESTS WHERE id_user = ?;";
        PreparedStatement extractRequestsStatement = connection.prepareStatement(requestsSelectStatement);
        extractRequestsStatement.setInt(1, idUser);
        ResultSet rs = extractRequestsStatement.executeQuery();

        if (!rs.next()) return null;
        int id = rs.getInt("id");
        String status = rs.getString("status");
        String dates = rs.getString("dates");
        Date dateOfSend = rs.getDate("date_of_send");
        Request newRequest = new Request(id, idUser, status, dates,dateOfSend);
        requests.add(newRequest);

        return newRequest;
    }


    public int addRequest(Request request) throws SQLException {
        String insertSQL = "INSERT INTO REQUESTS (id_user, status, dates, date_of_send) VALUES (?, ?, ?, ?);";
        PreparedStatement insertStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
        insertStatement.setInt(1, request.getIdUser());
        insertStatement.setString(2, request.getStatus());
        insertStatement.setString(3, request.getDates());
        insertStatement.setDate(4, request.getDateOfSend());
        insertStatement.execute();
        ResultSet rs = insertStatement.getGeneratedKeys();
        if (rs.next()) {
            long id = rs.getLong(1);
            request.setId((int) id);
        }
        return request.getId();
    }



    public Request findById (int id) throws SQLException {
        for (Request it : requests) {
            if (it.getId()==(id))
                return it;
        }

        String requestsSelectStatement = "SELECT * FROM requests WHERE id = ?;";
        PreparedStatement extractRequestsStatement = connection.prepareStatement(requestsSelectStatement);
        extractRequestsStatement.setInt(1, id);
        ResultSet rs = extractRequestsStatement.executeQuery();

        if (!rs.next()) return null;
        String status = rs.getString("status");
        int idUser = rs.getInt("id_user");
        String dates = rs.getString("dates");
        Date dateOfSend = rs.getDate("date_of_send");
        Request newRequest = new Request(id, idUser, status, dates, (java.sql.Date) dateOfSend);
        requests.add(newRequest);

        return newRequest;
    }
    public List<Request> findAll() throws SQLException {
        List<Request> all = new ArrayList<>();

        String requestSelectStatement = "SELECT id FROM requests;";
        Statement extractRequestStatement = connection.createStatement();
        ResultSet rs = extractRequestStatement.executeQuery(requestSelectStatement);

        while (rs.next()) {
            all.add(findById(rs.getInt("id")));
        }

        return all;
    }

    public int updateRequest(Request request) throws SQLException{
        String updateSQL = "UPDATE REQUESTS set status = ?, dates = ?, date_of_send= ? WHERE id_user = ?;";
        PreparedStatement updateStatus = connection.prepareStatement(updateSQL);
        updateStatus.setString(1, request.getStatus());
        updateStatus.setString(2, request.getDates());
        updateStatus.setDate(3, request.getDateOfSend());
        updateStatus.setInt(4, request.getIdUser());
        updateStatus.execute();
        requests.clear();
        List<Request> list = findAll();
        for (Request it : list) {
            requests.add(it);
        }
        return request.getId();
    }
    @Override
    public void update(Request request) throws SQLException {
        if(!(requests.contains(request))){
            addRequest(request);
            requests.add(request);
        }else {
            updateRequest(request);
        }
    }

    @Override
    public void update() throws SQLException {
        for (Request it : requests)
            update(it);
    }

    @Override
    public void clear() {
        requests.clear();
    }
}

