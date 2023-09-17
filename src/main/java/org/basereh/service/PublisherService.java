package org.basereh.service;

import lombok.RequiredArgsConstructor;
import org.basereh.LibraryException;
import org.basereh.dao.PublisherDao;
import org.basereh.domain.Publisher;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class PublisherService {
    private final PublisherDao dao;

    public List<Publisher> getAllPublishers() throws SQLException {     // todo comment SQLException
        return dao.getAll();
    }

    public Publisher getPublisher(Integer id) throws SQLException {
        return dao.get(id);
    }

    public void createPublisher(Publisher publisher) throws LibraryException {
        try {
            dao.save(publisher);
        } catch (SQLException e) {
            throw new LibraryException("Create new publisher failed!");
        }
    }

    public void updatePublisher(Integer id, Publisher updatedPublisher) throws LibraryException {
        try {
            dao.update(id, updatedPublisher);
        } catch (SQLException e) {
            throw new LibraryException("Update publisher failed!");
        }
    }

    public void deletePublisher(Integer id) throws LibraryException {
        try {
            dao.delete(id);
        } catch (SQLException e) {
            throw new LibraryException("Delete publisher failed!");
        }
    }
}
