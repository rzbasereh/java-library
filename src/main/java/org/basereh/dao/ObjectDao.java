package org.basereh.dao;

import org.basereh.CLIException;

import java.sql.SQLException;
import java.util.List;

public interface ObjectDao<T> {
    public List<T> getAll() throws SQLException;

    public void save(T obj) throws SQLException, CLIException;
}
