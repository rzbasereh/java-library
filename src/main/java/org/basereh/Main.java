package org.basereh;

import org.basereh.dao.AuthorDao;
import org.basereh.dao.BookDao;
import org.basereh.dao.PublisherDao;
import org.basereh.service.AuthorService;
import org.basereh.service.BookService;
import org.basereh.service.PublisherService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Scanner scanner = new Scanner(System.in)) {
            SQLDatabase sqlDatabase = new SQLDatabase("mysql://localhost:3306", "root", "root");
            sqlDatabase.setup();
            try (Connection connection = sqlDatabase.getConnection()) {
                CLI cli = getCli(connection, scanner);
                cli.mainLoop();
            }
        }
    }

    private static CLI getCli(Connection connection, Scanner scanner) {
        AuthorDao authorDao = new AuthorDao(connection);
        PublisherDao publisherDao = new PublisherDao(connection);
        BookDao bookDao = new BookDao(connection);

        AuthorService authorService = new AuthorService(authorDao);
        PublisherService publisherService = new PublisherService(publisherDao);
        BookService bookService = new BookService(bookDao);

        return new CLI(scanner, authorService, publisherService, bookService);
    }
}