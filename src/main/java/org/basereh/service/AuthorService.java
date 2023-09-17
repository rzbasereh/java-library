package org.basereh.service;

import lombok.RequiredArgsConstructor;
import org.basereh.LibraryException;
import org.basereh.dao.AuthorDao;
import org.basereh.domain.Author;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class AuthorService {
    private final AuthorDao dao;

    public List<Author> getAllAuthors() throws SQLException {
        return dao.getAll();
    }

    public Author getAuthor(Integer id) throws SQLException {
        return dao.get(id);
    }

    public void createAuthor(Author author) throws LibraryException {       // todo comment return object or id
        try {
            dao.save(author);
        } catch (SQLException e) {
            throw new LibraryException("Create new author failed!");
        }
    }

    public void updateAuthor(Integer id, Author updatedAuthor) throws LibraryException {
        try {
            dao.update(id, updatedAuthor);
        } catch (SQLException e) {
            throw new LibraryException("Update author failed!");
        }
    }

    public void deleteAuthor(Integer id) throws LibraryException {
        try {
            dao.delete(id);
        } catch (SQLException e) {
            throw new LibraryException("Delete author failed!");
        }
    }
}
