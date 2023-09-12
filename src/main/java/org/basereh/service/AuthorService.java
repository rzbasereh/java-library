package org.basereh.service;

import lombok.RequiredArgsConstructor;
import org.basereh.CLIException;
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


    public void createAuthor(Author author) throws SQLException, CLIException {
        dao.save(author);
    }
}
