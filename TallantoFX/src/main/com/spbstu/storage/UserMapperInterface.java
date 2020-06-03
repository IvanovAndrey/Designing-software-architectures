package main.com.spbstu.storage;



import main.com.spbstu.exceptions.EndBeforeStartException;

import java.sql.SQLException;

public interface UserMapperInterface<T> extends Mapper<T> {
    T findByLogin(String login) throws SQLException, EndBeforeStartException;
}
