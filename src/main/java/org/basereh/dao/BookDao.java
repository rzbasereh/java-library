package org.basereh.dao;

import lombok.RequiredArgsConstructor;
import org.basereh.CLIException;
import org.basereh.domain.Book;
import org.basereh.domain.Publisher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BookDao implements ObjectDao<Book> {
    private final Connection connection;

    @Override
    public List<Book> getAll() throws SQLException {
        List<Book> books = new ArrayList<>();
//        try (Statement statement = connection.createStatement()) {
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM book");
//            while (resultSet.next()) {
//                books.add(
//                        Book.builder()
//                                .id(resultSet.getInt("id"))
//                                .name(resultSet.getString("name"))
//                                .build()
//                );
//            }
//        }
        return books;
    }

    @Override
    public void save(Book book) throws SQLException, CLIException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into book (name, publisher_id, author_id) values (?,?,?)"
        )) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getPublisher().getId());
            preparedStatement.setInt(3, book.getAuthor().getId());
            int out = preparedStatement.executeUpdate();
            if (out == 0) {
                throw new CLIException("Create new book failed!");
            }
        }
    }
}
