package org.basereh.service;

import lombok.RequiredArgsConstructor;
import org.basereh.domain.Author;
import org.basereh.dao.AuthorDao;

import java.util.List;

@RequiredArgsConstructor
public class AuthorService {
    private final AuthorDao dao;

    public List<Author> retrieveAll() {
        return dao.getAll();
    }
}
