package main.com.spbstu.storage.user;



import main.com.spbstu.exceptions.EndBeforeStartException;
import main.com.spbstu.storage.Mapper;

import java.sql.SQLException;

public interface UserMapperInterface<T> extends Mapper<T> {
    T findByLogin(String login) throws SQLException, EndBeforeStartException;
}
