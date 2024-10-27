package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setup() {
        library = new Library();
        book1 = new Book("Test 1", "Michael Karpov", 2024);
        book2 = new Book("Test 2", "Michael Karpov", 2003);
        book3 = new Book("Another Test", "John Wayne", 1937);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
    }

    @Test
    public void testAddBook() {
        Book newBook = new Book("Testing4", "George Bush", 1956);
        library.addBook(newBook);

        List<Book> allBooks = library.getAllBooks();
        assertEquals(4, allBooks.size());

        boolean containsNewBook = allBooks.stream().anyMatch(book ->
            book.getTitle().equals(newBook.getTitle()) &&
            book.getAuthor().equals(newBook.getAuthor()) &&
            book.getYear() == newBook.getYear()
        );
        assertTrue(containsNewBook);
    }

    @Test
    public void testRemoveBook() {
        boolean removed = library.removeBook(new Book("Another Test", "John Wayne", 1937));
        assertTrue(removed);

        List<Book> allBooks = library.getAllBooks();
        assertEquals(2, allBooks.size());

        boolean containsBook = allBooks.stream().anyMatch(book ->
            book.getTitle().equals("Another Test") &&
            book.getAuthor().equals("John Wayne") &&
            book.getYear() == 1937
        );
        assertFalse(containsBook);
    }

    @Test
    public void testRemoveBookNotInLibrary() {
        Book nonExistentBook = new Book("Test5", "Peter Pan", 1990);
        boolean removed = library.removeBook(nonExistentBook);
        assertFalse(removed);
    }

    @Test
    public void testGetBooksByAuthor() {
        List<Book> orwellBooks = library.getBooksByAuthor("Michael Karpov");
        assertEquals(2, orwellBooks.size());
    }

    @Test
    public void testGetBooksByAuthorCaseInsensitive() {
        List<Book> orwellBooks = library.getBooksByAuthor("michael karpov");
        assertEquals(2, orwellBooks.size());
    }

    @Test
    public void testGetBooksByYear() {
        List<Book> booksFrom2024 = library.getBooksByYear(2024);
        assertEquals(1, booksFrom2024.size());
        assertEquals("Test 1", booksFrom2024.get(0).getTitle());
    }

    @Test
    public void testGetBooksByYearNoResults() {
        List<Book> booksFrom2000 = library.getBooksByYear(2000);
        assertTrue(booksFrom2000.isEmpty());
    }

    @Test
    public void testGetAllBooks() {
        List<Book> allBooks = library.getAllBooks();
        assertEquals(3, allBooks.size());
    }
}