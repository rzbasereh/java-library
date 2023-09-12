package org.basereh;

import org.basereh.dao.AuthorDao;
import org.basereh.dao.BookDao;
import org.basereh.dao.PublisherDao;
import org.basereh.service.AuthorService;
import org.basereh.service.BookService;
import org.basereh.service.PublisherService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Scanner scanner = new Scanner(System.in).useDelimiter("\n")) {
            try (Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/library", "root", "root")) {
                AuthorDao authorDao = new AuthorDao(connection);
                PublisherDao publisherDao = new PublisherDao(connection);
                BookDao bookDao = new BookDao(connection, authorDao, publisherDao);

                AuthorService authorService = new AuthorService(authorDao);
                PublisherService publisherService = new PublisherService(publisherDao);
                BookService bookService = new BookService(bookDao);

                CLI cli = new CLI(scanner, authorService, publisherService, bookService);
                cli.mainLoop();
            }
        }
    }
}