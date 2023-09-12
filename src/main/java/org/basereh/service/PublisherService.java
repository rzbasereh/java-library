package org.basereh.service;

import lombok.RequiredArgsConstructor;
import org.basereh.CLIException;
import org.basereh.dao.PublisherDao;
import org.basereh.domain.Publisher;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class PublisherService {
    private final PublisherDao dao;

    public List<Publisher> getAllPublishers() throws SQLException {
        return dao.getAll();
    }


    public void createPublisher(Publisher publisher) throws SQLException, CLIException {
        dao.save(publisher);
    }
}
