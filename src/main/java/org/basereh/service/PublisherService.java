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

    public List<Publisher> getAllPublishers() {
        try {
            return dao.getAll();
        } catch (SQLException e) {      // todo comment sohbat
            throw new LibraryException("get all publishers failed!");
        }
    }

    public Publisher getPublisher(Integer id) {
        try {
            return dao.get(id);
        } catch (SQLException e) {
            throw new LibraryException("get publisher failed!");
        }
    }

    public Publisher createPublisher(Publisher publisher) {
        try {
            return dao.save(publisher);
        } catch (SQLException e) {
            throw new LibraryException("Create new publisher failed!");
        }
    }

    public void updatePublisher(Integer id, Publisher updatedPublisher) {
        try {
            dao.update(id, updatedPublisher);
        } catch (SQLException e) {
            throw new LibraryException("Update publisher failed!");
        }
    }

    public void deletePublisher(Integer id) {
        try {
            dao.delete(id);
        } catch (SQLException e) {
            throw new LibraryException("Delete publisher failed!");
        }
    }
}
