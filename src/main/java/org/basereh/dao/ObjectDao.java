package org.basereh.dao;

import java.sql.SQLException;
import java.util.List;

public interface ObjectDao<T> {
    List<T> getAll() throws SQLException;

    T get(Integer id) throws SQLException;

    T save(T obj) throws SQLException;

    void update(Integer id, T updatedObj) throws SQLException;

    void delete(Integer id) throws SQLException;
}
