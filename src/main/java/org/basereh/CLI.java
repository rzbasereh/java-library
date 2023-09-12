package org.basereh;

import lombok.RequiredArgsConstructor;
import org.basereh.domain.Author;
import org.basereh.domain.Book;
import org.basereh.domain.Publisher;
import org.basereh.service.AuthorService;
import org.basereh.service.BookService;
import org.basereh.service.PublisherService;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class CLI {
    private final Scanner scanner;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final BookService bookService;

    public void mainLoop() throws SQLException {
        List<String> options = Arrays.asList(
                "Show all authors",
                "Show all publishers",
                "Show all books",
                "Add new author",
                "Add new publisher",
                "Add new book",
                "Get Single author",
                "Get Single publishers",
                "Get Single book"
        );

        do {
            try {
                switch (selectOption("Please select one of these options:", options)) {
                    case 0 -> {
                        System.out.println(authorService.getAllAuthors());
                    }
                    case 1 -> {
                        System.out.println(publisherService.getAllPublishers());
                    }
                    case 2 -> {
                        System.out.println(bookService.getAllBooks());
                    }
                    case 3 -> {
                        String firstName = getText("Enter firstname of author:");
                        String lastName = getText("Enter lastname of author:");
                        authorService.createAuthor(Author.builder().firstname(firstName).lastname(lastName).build());
                        System.out.println("Author added successfully!");
                    }
                    case 4 -> {
                        String name = getText("Enter name of publisher:");
                        publisherService.createPublisher(Publisher.builder().name(name).build());
                        System.out.println("Publisher added successfully!");
                    }
                    case 5 -> {
                        String name = getText("Enter name of book:");

                        List<Publisher> publishers = publisherService.getAllPublishers();
                        Publisher publisher = publishers.get(selectOption(
                                "Select book publisher:",
                                publishers.stream().map(Publisher::toString).toList())
                        );

                        List<Author> authors = authorService.getAllAuthors();
                        Author author = authors.get(selectOption(
                                "Select book author:",
                                authors.stream().map(Author::toString).toList())
                        );

                        bookService.createBook(
                                Book.builder().name(name).publisher(publisher).author(author).build()
                        );
                        System.out.println("Book added successfully!");
                    }
                    case 6 -> {
                        Integer id = getId("Enter author id:");
                        System.out.println(authorService.getAuthor(id));
                    }
                    case 7 -> {
                        Integer id = getId("Enter publisher id:");
                        System.out.println(publisherService.getPublisher(id));
                    }
                    case 8 -> {
                        Integer id = getId("Enter book id:");
                        System.out.println(bookService.getBook(id));
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

    private String getText(String title) {
        System.out.println(title);
        return scanner.next();
    }

    private Integer getId(String title) {
        System.out.println(title);
        return scanner.nextInt();
    }

    private boolean isContinue() {
        System.out.print("\nAre you want to continue (Y/n): ");
        String res = scanner.next();
        return !res.equals("n");
    }
}
