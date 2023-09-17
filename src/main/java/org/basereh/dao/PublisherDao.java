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
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM publisher WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
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
    public Publisher save(Publisher publisher) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into publisher (name) values (?)"
        )) {
            preparedStatement.setString(1, publisher.getName());
            int out = preparedStatement.executeUpdate();
            if (out == 0) {
                throw new SQLException("Creating publisher failed, no rows affected.");
            }

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return Publisher.builder()
                            .id(resultSet.getInt(1))
                            .name(publisher.getName())
                            .build();
                } else {
                    throw new SQLException("Creating publisher failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public void update(Integer id, Publisher updatedPublisher) throws SQLException {
        if (get(id) != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE book SET name=? WHERE id=?")) {
                preparedStatement.setString(1, updatedPublisher.getName());
                preparedStatement.setInt(2, id);
                int out = preparedStatement.executeUpdate();
                if (out == 0) {
                    throw new SQLException("Updating publisher failed, no ID obtained.");
                }
            }
        } else {
            throw new SQLException("Updating publisher failed, no publisher exist.");
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM publisher WHERE id=?")) {
            preparedStatement.setInt(1, id);
            int out = preparedStatement.executeUpdate();
            if (out == 0) {
                throw new SQLException("Deleting publisher failed, no rows affected.");
            }
        }
    }
}
