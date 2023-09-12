package org.basereh.service;

import lombok.RequiredArgsConstructor;
import org.basereh.CLIException;
import org.basereh.dao.AuthorDao;
import org.basereh.dao.BookDao;
import org.basereh.domain.Author;
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

    public void createBook(Book author) throws SQLException, CLIException {
        dao.save(author);
    }

    public void deleteBook(Integer id) throws SQLException {
        dao.delete(id);
    }
}
