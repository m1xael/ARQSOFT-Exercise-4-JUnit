package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book) {
        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            if (currentBook.getTitle().equals(book.getTitle()) &&
                currentBook.getAuthor().equals(book.getAuthor()) &&
                currentBook.getYear() == book.getYear()) {
                books.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Book> getBooksByAuthor(String author) {
        return books.stream()
                    .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                    .collect(Collectors.toList());
    }

    public List<Book> getBooksByYear(int year) {
        return books.stream()
                    .filter(b -> b.getYear() == year)
                    .collect(Collectors.toList());
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }
}