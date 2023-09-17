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

    public void mainLoop() {
        List<String> options = Arrays.asList(       // todo comment private static final
                "Show all authors",
                "Show all publishers",
                "Show all books",
                "Add new author",
                "Add new publisher",
                "Add new book",
                "Get Single author",
                "Get Single publishers",
                "Get Single book",
                "Delete an author",
                "Delete a publishers",
                "Delete a book",
                "Update an author",
                "Update a publishers",
                "Update a book"
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
                        Publisher publisher = selectPublisher();
                        Author author = selectAuthor();

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
                    case 9 -> {
                        Integer id = getId("Enter author id:");
                        authorService.deleteAuthor(id);
                        System.out.println("Author with id " + id + " deleted successfully!");
                    }
                    case 10 -> {
                        Integer id = getId("Enter publisher id:");
                        publisherService.deletePublisher(id);
                        System.out.println("Publisher with id " + id + " deleted successfully!");

                    }
                    case 11 -> {
                        Integer id = getId("Enter book id:");
                        bookService.deleteBook(id);
                        System.out.println("Book with id " + id + " deleted successfully!");
                    }
                    case 12 -> {
                        Author author = selectAuthor();
                        String firstName = getText("Enter new firstname:");
                        String lastName = getText("Enter new lastname:");
                        authorService.updateAuthor(
                                author.getId(),
                                Author.builder().firstname(firstName).lastname(lastName).build()
                        );
                        System.out.println("Author updated successfully!");
                    }
                    case 13 -> {
                        Publisher publisher = selectPublisher();
                        String name = getText("Enter new name:");
                        publisherService.updatePublisher(publisher.getId(), Publisher.builder().name(name).build());
                        System.out.println("Publisher updated successfully!");
                    }
                    case 14 -> {
                        Book book = selectBook();
                        String name = getText("Enter name of book:");
                        Publisher publisher = selectPublisher();
                        Author author = selectAuthor();
                        bookService.updateBook(
                                book.getId(),
                                Book.builder().name(name).publisher(publisher).author(author).build()
                        );
                        System.out.println("Book updated successfully!");
                    }
                }
            } catch (LibraryException e) {
                System.out.println(e.getMessage());
            } catch (Exception ignored) {}      // todo comment chera?
        } while (isContinue());
    }

    private Publisher selectPublisher() throws SQLException, LibraryException {
        List<Publisher> publishers = publisherService.getAllPublishers();
        return publishers.get(selectOption(
                "Select a publisher:",
                publishers.stream().map(Publisher::toString).toList())      // todo comment id lozooman indexe mage?
        );
    }

    private Author selectAuthor() throws SQLException, LibraryException {
        List<Author> authors = authorService.getAllAuthors();
        return authors.get(selectOption(
                "Select an author:",
                authors.stream().map(Author::toString).toList())
        );
    }

    private Book selectBook() throws SQLException, LibraryException {
        List<Book> books = bookService.getAllBooks();
        return books.get(selectOption(
                "Select a book:",
                books.stream().map(Book::toString).toList())
        );
    }

    private int selectOption(String title, List<String> options) throws LibraryException {
        System.out.println("\n" + title);
        for (int i = 0; i < options.size(); i++) {
            System.out.println("\t[" + (i + 1) + "] " + options.get(i));
        }

        int selectedOptionIndex = scanner.nextInt() - 1;
        if (selectedOptionIndex < 0 || selectedOptionIndex >= options.size()) {
            throw new LibraryException("Invalid option selected!");
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
        System.out.print("\nAre you want to continue (Y/n): ");     // todo comment Do
        String res = scanner.next();
        return !res.equals("n");
    }
}
