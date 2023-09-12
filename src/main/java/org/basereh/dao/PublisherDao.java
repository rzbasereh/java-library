package org.basereh.dao;

import lombok.RequiredArgsConstructor;
import org.basereh.domain.Publisher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PublisherDao implements ObjectDao<Publisher> {
    private final Connection connection;

    @Override
    public List<Publisher> getAll() throws SQLException {
        List<Publisher> publishers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM publisher");
            while (resultSet.next()) {
                publishers.add(
                        Publisher.builder()
                                .id(resultSet.getInt("id"))
                                .name(resultSet.getString("name"))
                                .build()
                );
            }
        }
        return publishers;
    }

    @Override
    public Publisher get(Integer id) throws SQLException {
        Publisher publisher = null;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM publisher WHERE id = " + id);
            if (resultSet.next()) {
                publisher = Publisher.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();
            }
        }
        return publisher;
    }

    @Override
    public void save(Publisher publisher) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into publisher (name) values (?)"
        )) {
            preparedStatement.setString(1, publisher.getName());
            int out = preparedStatement.executeUpdate();
            if (out == 0) {
                throw new SQLException("Create new publisher failed!");
            }
        }
    }
}
