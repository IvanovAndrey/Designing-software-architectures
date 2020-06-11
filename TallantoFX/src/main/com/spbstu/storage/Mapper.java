package main.com.spbstu.storage;


import main.com.spbstu.exceptions.EndBeforeStartException;

import java.sql.SQLException;
import java.util.List;


public interface Mapper<T> {
    T findById(int id) throws SQLException, EndBeforeStartException;
    List<T> findAll() throws SQLException, EndBeforeStartException;
    void update(T item) throws SQLException;
    void update() throws SQLException;
    void clear();
}

