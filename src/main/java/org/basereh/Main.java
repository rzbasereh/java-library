package org.basereh;

import org.basereh.dao.AuthorDao;
import org.basereh.service.AuthorService;

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

                AuthorService authorService = new AuthorService(authorDao);

                CLI cli = new CLI(scanner, authorService);
                cli.mainLoop();
            }
        }
    }
}