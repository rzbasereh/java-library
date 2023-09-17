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

    public List<Author> getAllAuthors() {
        try {
            return dao.getAll();
        } catch (SQLException e) {
            throw new LibraryException("get all authors failed!");
        }
    }

    public Author getAuthor(Integer id) {
        try {
            return dao.get(id);
        } catch (SQLException e) {
            throw new LibraryException("get author failed!");
        }
    }

    public Author createAuthor(Author author) {
        try {
            return dao.save(author);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new LibraryException("Create new author failed!");
        }
    }

    public void updateAuthor(Integer id, Author updatedAuthor) {
        try {
            dao.update(id, updatedAuthor);
        } catch (SQLException e) {
            throw new LibraryException("Update author failed!");
        }
    }

    public void deleteAuthor(Integer id) {
        try {
            dao.delete(id);
        } catch (SQLException e) {
            throw new LibraryException("Delete author failed!");
        }
    }
}
