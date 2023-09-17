package org.basereh.dao;

import lombok.RequiredArgsConstructor;
import org.basereh.domain.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class AuthorDao implements ObjectDao<Author> {
    private final Connection connection;

    @Override
    public List<Author> getAll() throws SQLException {
        List<Author> authors = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM author");
            while (resultSet.next()) {
                authors.add(
                        Author.builder()
                                .id(resultSet.getInt("id"))
                                .firstname(resultSet.getString("first_name"))
                                .lastname(resultSet.getString("last_name"))
                                .build()
                );
            }
        }
        return authors;
    }

    @Override
    public Author get(Integer id) throws SQLException {
        Author author = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM author WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                author = Author.builder()
                        .id(resultSet.getInt("id"))
                        .firstname(resultSet.getString("first_name"))
                        .lastname(resultSet.getString("last_name"))
                        .build();
            }
        }
        return author;
    }

    @Override
    public Author save(Author author) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO author (first_name, last_name) VALUES (?,?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setString(1, author.getFirstname());
            preparedStatement.setString(2, author.getLastname());
            int out = preparedStatement.executeUpdate();
            if (out == 0) {
                throw new SQLException("Creating author failed, no rows affected.");
            }

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return Author.builder()
                            .id(resultSet.getInt(1))
                            .firstname(author.getFirstname())
                            .lastname(author.getLastname())
                            .build();
                } else {
                    throw new SQLException("Creating author failed, no ID obtained.");
                }
            }
        }
    }

    @Override
    public void update(Integer id, Author updatedAuthor) throws SQLException {      // todo comment validation
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE author SET first_name=?, last_name=? WHERE id=?"
        )) {
            preparedStatement.setString(1, updatedAuthor.getFirstname());
            preparedStatement.setString(2, updatedAuthor.getLastname());
            preparedStatement.setInt(3, id);
            int out = preparedStatement.executeUpdate();
            if (out == 0) {
                throw new SQLException();
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM author WHERE id=?")) {
            preparedStatement.setInt(1, id);
            int out = preparedStatement.executeUpdate();
            if (out == 0) {
                throw new SQLException();
            }
        }
    }
}
