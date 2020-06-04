package main.com.spbstu.storage;

import main.com.spbstu.exceptions.DBConnectionException;
import main.com.spbstu.exceptions.IncorrectPasswordException;
import main.com.spbstu.exceptions.UserAlreadyExistsException;
import main.com.spbstu.exceptions.UserNotFoundException;
import main.com.spbstu.project.Complaint;
import main.com.spbstu.storage.project.ComplaintMapper;
import main.com.spbstu.storage.user.UserMapper;
import main.com.spbstu.user.User;

import java.io.IOException;
import java.sql.SQLException;

public class StorageRepository {

    private static UserMapper userMapper;
    private static ComplaintMapper complaintMapper;
    public StorageRepository() {
    try {
        if (userMapper == null) userMapper = new UserMapper();
        if (complaintMapper == null) complaintMapper = new ComplaintMapper();
       /* if (projectMapper == null) projectMapper = new ProjectMapper(managerMapper);
        if (teamLeaderMapper == null) teamLeaderMapper = new TeamLeaderMapper(projectMapper);
        if (developerMapper == null) developerMapper = new DeveloperMapper(projectMapper);
        if (testerMapper == null) testerMapper = new TesterMapper(projectMapper);*/
    } catch (
    SQLException e) {
        e.printStackTrace();
    } catch (
    IOException e) {
        e.printStackTrace();
    }
}

    public void addUser(String login, String name, String status, String password)
            throws UserAlreadyExistsException, DBConnectionException {
        try {
            if (userMapper.findByLogin(login) != null)
                throw new UserAlreadyExistsException(login);

            User newUser = new User(0, login, name, status);
            userMapper.addUser(newUser, password);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public User getUser(String login) throws UserNotFoundException, DBConnectionException {
        try {
            User user = userMapper.findByLogin(login);
            if (user == null) throw new UserNotFoundException(login);
            return user;
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }
/*
    public Manager getManager(User user) throws EndBeforeStartException, DBConnectionException {
        try {
            return managerMapper.findByLogin(user.getLogin());
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public TeamLeader getTeamLeader(User user) throws EndBeforeStartException, DBConnectionException {
        try {
            return teamLeaderMapper.findByLogin(user.getLogin());
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public Developer getDeveloper(User user) throws EndBeforeStartException, DBConnectionException {
        try {
            return developerMapper.findByLogin(user.getLogin());
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public Tester getTester(User user) throws EndBeforeStartException, DBConnectionException, UserAlreadyHasRoleException {
        try {
            return testerMapper.findByLogin(user.getLogin());
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }
*/
/*
    public void authenticateUser(String login, String password) throws DBConnectionException, IncorrectPasswordException {
        // replase UserNotFound exception with IncorrectPassword exception,
        // so nobody can find out if login was correct or not
        try {
            getUser(login).signIn(password);
        } catch (UserNotFoundException e) {
            throw new IncorrectPasswordException();
        }
    }

    public void authenticateUser(User user, String password) throws DBConnectionException, IncorrectPasswordException {
        try {
            if (!userMapper.authenticateUser(user, password))
                throw new IncorrectPasswordException();
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }
*/
public User authenticateUser(String login, String password) throws DBConnectionException, IncorrectPasswordException {
    try {
        User user = getUser(login);
        if (!userMapper.authenticateUser(user, password))
        throw new IncorrectPasswordException();
        else
        return user;
    } catch (UserNotFoundException | SQLException e) {
        throw new DBConnectionException();
    }
}

    public void addComplaint(int idIncedent, String theme, String text)
            throws DBConnectionException  {
        try {
            Complaint complaint = new Complaint(0, idIncedent, theme, text);
            complaintMapper.addComplaint(complaint);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }

    public Complaint getComplaint(int idIncedent) throws DBConnectionException {
        try {
            return complaintMapper.findByIdIncedent(idIncedent);
        } catch (SQLException e) {
            throw new DBConnectionException();
        }
    }


/*
    public void clear() {
        userMapper.clear();
        managerMapper.clear();
        projectMapper.clear();
        teamLeaderMapper.clear();
        developerMapper.clear();
        testerMapper.clear();
    }

    public void update() throws SQLException {
        userMapper.update();
        managerMapper.update();
        projectMapper.update();
        teamLeaderMapper.update();
        developerMapper.update();
        testerMapper.update();
    }

    synchronized public void drop() throws DBConnectionException {
        try {
            DataGateway.getInstance().dropAll();
        } catch (SQLException e) {
            throw new DBConnectionException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
