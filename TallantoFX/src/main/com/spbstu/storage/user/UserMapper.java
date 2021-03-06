package main.com.spbstu.storage.user;



import main.com.spbstu.storage.DataGateway;
import main.com.spbstu.user.User;

import java.io.IOException;
import java.sql.*;
import java.util.*;

public class UserMapper implements UserMapperInterface<User> {

    private static Set<User> users = new HashSet<>();
    private Connection connection;

    public UserMapper() throws SQLException, IOException {

        DataGateway gateway = DataGateway.getInstance();
        connection = gateway.getDataSource().getConnection();
    }

    public boolean addUser(User user, String password) throws SQLException {
        String insertSQL = "INSERT INTO USERS(name, login, status, password) VALUES (?, ?, ?, ?);";
        PreparedStatement insertStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
        insertStatement.setString(1, user.getName());
        insertStatement.setString(2, user.getLogin());
        insertStatement.setString(3, user.getStatus());
        insertStatement.setString(4, password);
        insertStatement.execute();
        ResultSet rs = insertStatement.getGeneratedKeys();
        if (rs.next()) {
            long id = rs.getLong(1);
            user.setId((int) id);
        }
        return true;
    }

    public boolean authenticateUser(User user, String password) throws SQLException {
        String userSelectStatement = "SELECT password FROM USERS WHERE id = ?;";
        PreparedStatement extractUserStatement = connection.prepareStatement(userSelectStatement);
        extractUserStatement.setInt(1, user.getId());
        ResultSet rs = extractUserStatement.executeQuery();

        if (!rs.next()) return false;
        return password.equals(rs.getString("password").replaceAll("\\s+",""));
    }

    @Override
    public User findByLogin(String login) throws SQLException {
        for (User it : users) {
            if (it.getLogin().equals(login))
                return it;
        }

        String userSelectStatement = "SELECT * FROM USERS WHERE login = ?;";
        PreparedStatement extractUserStatement = connection.prepareStatement(userSelectStatement);
        extractUserStatement.setString(1, login);
        ResultSet rs = extractUserStatement.executeQuery();

        if (!rs.next()) return null;
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String status = rs.getString("status");


        User newUser = new User(id, login, name, status);
        users.add(newUser);

        return newUser;
    }

    @Override
    public User findById(int id) throws SQLException {
        for (User it : users) {
            if (it.getId() == id)
                return it;
        }

        String selectSQL = "SELECT * FROM USERS WHERE id = ?;";
        PreparedStatement extractUserStatement = connection.prepareStatement(selectSQL);
        extractUserStatement.setInt(1, id);
        ResultSet rs = extractUserStatement.executeQuery();

        if (!rs.next()) return null;

        int uid = rs.getInt("id");
        String login = rs.getString("login");
        String name = rs.getString("name");
        String status = rs.getString("status");

        User newUser = new User(id, login, name, status);


        users.add(newUser);

        return newUser;
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> all = new ArrayList<>();

        String userSelectStatement = "SELECT id FROM USERS;";
        Statement extractUserStatement = connection.createStatement();
        ResultSet rs = extractUserStatement.executeQuery(userSelectStatement);

        while (rs.next()) {
            all.add(findById(rs.getInt("id")));
        }

        return all;
    }

    @Override
    public List<User> findRole(String role) throws SQLException {
        List<User> all = new ArrayList<>();

        String userSelectStatement = "SELECT id FROM USERS WHERE status = ?;";
        PreparedStatement extractUserStatement = connection.prepareStatement(userSelectStatement);
        extractUserStatement.setString(1, role);
        ResultSet rs = extractUserStatement.executeQuery();

        while (rs.next()) {
            all.add(findById(rs.getInt("id")));
        }

        return all;
    }

    @Override
    public void update(User item) throws SQLException {
        if (users.contains(item)) {

        } else {
            String generatedColumns[] = { "USERS.id" };
            String insertSQL = "INSERT INTO USERS(name, login, status) VALUES (?, ?, ?);";
            PreparedStatement insertStatement = connection.prepareStatement(insertSQL, generatedColumns);
            insertStatement.setString(1, item.getName());
            insertStatement.setString(2, item.getLogin());
            insertStatement.setObject(3, item.getStatus());
            insertStatement.execute();
            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong(1);
                item.setId((int) id);
            }
            users.add(item);
        }
    }


    public void closeConnection() throws SQLException {
        connection.close();
    }

    @Override
    public void clear() {
        users.clear();
    }

    @Override
    public void update() throws SQLException {
        for (User it : users)
            update(it);
    }

}
