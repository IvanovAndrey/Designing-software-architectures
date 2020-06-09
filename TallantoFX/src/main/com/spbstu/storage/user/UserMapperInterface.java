package main.com.spbstu.storage.user;



import main.com.spbstu.exceptions.EndBeforeStartException;
import main.com.spbstu.storage.Mapper;
import main.com.spbstu.user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserMapperInterface<T> extends Mapper<T> {
    T findByLogin(String login) throws SQLException, EndBeforeStartException;

    List<User> findRole(String role) throws SQLException;
}
