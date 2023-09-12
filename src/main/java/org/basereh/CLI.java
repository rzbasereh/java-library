package org.basereh;

import lombok.RequiredArgsConstructor;
import org.basereh.service.AuthorService;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class CLI {
    private final Scanner scanner;
    private final AuthorService authorService;

    public void mainLoop() {
        List<String> options = Arrays.asList(
                "Show all authors",
                "Show all publishers",
                "Show all books",
                "Add new author",
                "Add new publisher",
                "Add new book"
        );

        do {
            try {
                switch (selectOption("Please select one of these options:", options)) {
                    case 0 -> {
                        StringBuilder formatBuilder = new StringBuilder();
                        System.out.println(authorService.retrieveAll());
                    }
                }
            } catch (CLIException e) {
                System.out.println(e.getMessage());
            }
        } while (isContinue());
    }

    private int selectOption(String title, List<String> options) throws CLIException {
        System.out.println("\n" + title);
        for (int i = 0; i < options.size(); i++) {
            System.out.println("\t[" + (i + 1) + "] " + options.get(i));
        }

        int selectedOptionIndex = scanner.nextInt() - 1;
        if (selectedOptionIndex < 0 || selectedOptionIndex >= options.size()) {
            throw new CLIException("Invalid option selected!");
        }
        return selectedOptionIndex;
    }

    private boolean isContinue() {
        System.out.print("\nAre you want to continue (Y/n): ");
        String res = scanner.next();
        return !res.equals("n");
    }
}
