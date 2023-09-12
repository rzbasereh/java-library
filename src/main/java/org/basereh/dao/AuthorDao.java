package org.basereh.dao;

import lombok.RequiredArgsConstructor;
import org.basereh.CLIException;
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
    public void save(Author author) throws SQLException, CLIException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into author (first_name, last_name) values (?,?)"
        )) {
            preparedStatement.setString(1, author.getFirstname());
            preparedStatement.setString(2, author.getLastname());
            int out = preparedStatement.executeUpdate();
            if (out == 0){
                throw new CLIException("Create new author failed!");
            }
        }
    }
}
