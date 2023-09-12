package org.basereh.service;

import lombok.RequiredArgsConstructor;
import org.basereh.LibraryException;
import org.basereh.dao.BookDao;
import org.basereh.domain.Book;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class BookService {
    private final BookDao dao;

    public List<Book> getAllBooks() throws SQLException {
        return dao.getAll();
    }

    public Book getBook(Integer id) throws SQLException {
        return dao.get(id);
    }

    public void createBook(Book book) throws LibraryException {
        try {
            dao.save(book);
        } catch (SQLException e) {
            throw new LibraryException("Create new book failed!");
        }
    }

    public void updateBook(Integer id, Book updatedBook) throws LibraryException {
        try {
            dao.update(id, updatedBook);
        } catch (SQLException e) {
            throw new LibraryException("Update book failed!");
        }
    }

    public void deleteBook(Integer id) throws LibraryException {
        try {
            dao.delete(id);
        } catch (SQLException e) {
            throw new LibraryException("Delete book failed!");
        }
    }
}
