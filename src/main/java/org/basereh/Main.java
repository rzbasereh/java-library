package org.basereh;

import org.basereh.dao.AuthorDao;
import org.basereh.service.AuthorService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Scanner scanner = new Scanner(System.in)) {
            try (Connection con = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/library", "root", "root")) {
                try (Statement stmt = con.createStatement()) {
                    AuthorDao authorDao = new AuthorDao(stmt);

                    AuthorService authorService = new AuthorService(authorDao);

                    CLI cli = new CLI(scanner, authorService);
                    cli.mainLoop();
                }

            }
        }
    }
}