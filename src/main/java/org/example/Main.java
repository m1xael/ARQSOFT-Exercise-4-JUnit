package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Library Management System!");

        boolean exit = false;
        while (!exit) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addBookCLI();
                    break;
                case "2":
                    removeBookCLI();
                    break;
                case "3":
                    searchByAuthorCLI();
                    break;
                case "4":
                    searchByYearCLI();
                    break;
                case "5":
                    listAllBooksCLI();
                    break;
                case "6":
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("1 - Add a book");
        System.out.println("2 - Remove a book");
        System.out.println("3 - Search books by author");
        System.out.println("4 - Search books by year");
        System.out.println("5 - List all books");
        System.out.println("6 - Exit");
        System.out.print("Your choice: ");
    }

    private static void addBookCLI() {
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine().trim();

        System.out.print("Enter the year of publication: ");
        int year = Integer.parseInt(scanner.nextLine().trim());

        Book book = new Book(title, author, year);
        library.addBook(book);
        System.out.println("Book added successfully!");
    }

    private static void removeBookCLI() {
        System.out.print("Enter the title of the book to remove: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter the author of the book to remove: ");
        String author = scanner.nextLine().trim();

        System.out.print("Enter the year of publication of the book to remove: ");
        int year = Integer.parseInt(scanner.nextLine().trim());

        Book book = new Book(title, author, year);
        boolean removed = library.removeBook(book);

        if (removed) {
            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found in the library.");
        }
    }

    private static void searchByAuthorCLI() {
        System.out.print("Enter the author's name: ");
        String author = scanner.nextLine().trim();

        List<Book> booksByAuthor = library.getBooksByAuthor(author);

        if (!booksByAuthor.isEmpty()) {
            System.out.println("Books by " + author + ":");
            booksByAuthor.forEach(System.out::println);
        } else {
            System.out.println("No books found by that author.");
        }
    }

    private static void searchByYearCLI() {
        System.out.print("Enter the year of publication: ");
        int year = Integer.parseInt(scanner.nextLine().trim());

        List<Book> booksByYear = library.getBooksByYear(year);

        if (!booksByYear.isEmpty()) {
            System.out.println("Books published in " + year + ":");
            booksByYear.forEach(System.out::println);
        } else {
            System.out.println("No books found from that year.");
        }
    }

    private static void listAllBooksCLI() {
        List<Book> allBooks = library.getAllBooks();

        if (!allBooks.isEmpty()) {
            System.out.println("Listing all books in the library:");
            allBooks.forEach(System.out::println);
        } else {
            System.out.println("The library is empty.");
        }
    }
}