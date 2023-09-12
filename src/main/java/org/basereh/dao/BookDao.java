package org.basereh.dao;

import lombok.RequiredArgsConstructor;
import org.basereh.domain.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BookDao implements ObjectDao<Book> {
    private final Connection connection;
    private final AuthorDao authorDao;
    private final PublisherDao publisherDao;

    @Override
    public List<Book> getAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book");
            while (resultSet.next()) {
                books.add(
                        Book.builder()
                                .id(resultSet.getInt("id"))
                                .name(resultSet.getString("name"))
                                .author(authorDao.get(resultSet.getInt("author_id")))
                                .publisher(publisherDao.get(resultSet.getInt("publisher_id")))
                                .build()
                );
            }
        }
        return books;
    }

    @Override
    public Book get(Integer id) throws SQLException {
        Book book = null;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM book WHERE id = " + id);
            if (resultSet.next()) {
                book = Book.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .author(authorDao.get(resultSet.getInt("author_id")))
                        .publisher(publisherDao.get(resultSet.getInt("publisher_id")))
                        .build();
            }
        }
        return book;
    }

    @Override
    public void save(Book book) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into book (name, publisher_id, author_id) values (?,?,?)"
        )) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setInt(2, book.getPublisher().getId());
            preparedStatement.setInt(3, book.getAuthor().getId());
            int out = preparedStatement.executeUpdate();
            if (out == 0) {
                throw new SQLException();
            }
        }
    }

    @Override
    public void update(Integer id, Book updatedBook) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE book SET name=?, publisher_id=?,author_id=? WHERE id=?"
        )) {
            preparedStatement.setString(1, updatedBook.getName());
            preparedStatement.setInt(2, updatedBook.getPublisher().getId());
            preparedStatement.setInt(3, updatedBook.getAuthor().getId());
            preparedStatement.setInt(4, id);
            int out = preparedStatement.executeUpdate();
            if (out == 0) {
                throw new SQLException();
            }
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM book WHERE id=?")) {
            preparedStatement.setInt(1, id);
            int out = preparedStatement.executeUpdate();
            if (out == 0) {
                throw new SQLException();
            }
        }
    }
}
