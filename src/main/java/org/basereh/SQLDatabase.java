package org.basereh;

import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@RequiredArgsConstructor
public class SQLDatabase {
    private final String url;
    private final String username;
    private final String password;

    public void setup() throws SQLException {
        try(Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                ) {
            createDatabase(statement);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:" + url + "/library", username, password);
    }

    private void createDatabase(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS library");
    }
}

