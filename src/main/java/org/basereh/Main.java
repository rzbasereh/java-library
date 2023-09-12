package org.basereh;

import org.basereh.dao.AuthorDao;
import org.basereh.dao.PublisherDao;
import org.basereh.service.AuthorService;
import org.basereh.service.PublisherService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Scanner scanner = new Scanner(System.in)) {
            try (Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/library", "root", "root")) {
                AuthorDao authorDao = new AuthorDao(connection);
                PublisherDao publisherDao = new PublisherDao(connection);

                AuthorService authorService = new AuthorService(authorDao);
                PublisherService publisherService = new PublisherService(publisherDao);

                CLI cli = new CLI(scanner, authorService, publisherService);
                cli.mainLoop();
            }
        }
    }
}