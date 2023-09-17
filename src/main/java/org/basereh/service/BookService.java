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

    public List<Book> getAllBooks() {
        try {
            return dao.getAll();
        } catch (SQLException e) {
            throw new LibraryException("get all books failed!");
        }
    }

    public Book getBook(Integer id) {
        try {
            return dao.get(id);
        } catch (SQLException e) {
            throw new LibraryException("get book failed!");
        }
    }

    public void createBook(Book book) {
        try {
            dao.save(book);
        } catch (SQLException e) {
            throw new LibraryException("Create new book failed!");
        }
    }

    public void updateBook(Integer id, Book updatedBook) {
        try {
            dao.update(id, updatedBook);
        } catch (SQLException e) {
            throw new LibraryException("Update book failed!");
        }
    }

    public void deleteBook(Integer id) {
        try {
            dao.delete(id);
        } catch (SQLException e) {
            throw new LibraryException("Delete book failed!");
        }
    }
}
