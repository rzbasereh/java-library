package org.basereh;

import org.basereh.dao.AuthorDao;
import org.basereh.service.AuthorService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            AuthorDao authorRepository = new AuthorDao();

            AuthorService authorService = new AuthorService(authorRepository);

            CLI cli = new CLI(scanner, authorService);
            cli.mainLoop();
        }
    }
}