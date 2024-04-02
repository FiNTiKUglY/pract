package com.library.api.libraryapi.controllers;

import java.util.List;
import java.util.UUID;
import com.library.api.libraryapi.services.BookService;
import com.library.api.libraryapi.entities.Book;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/api/authors/{authorId}/books")
    public List<Book> getAuthorBooks(@PathVariable UUID authorId) {
        return bookService.getAuthorBooks(authorId);
    }

    @GetMapping("/api/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/api/books/{id}")
    public Book getBookById(@PathVariable UUID id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/api/books/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/api/books/update/{id}")
    public Book updateBook(@PathVariable UUID id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/api/books/delete/{id}")
    public void deleteBook(@PathVariable UUID id) {
        bookService.deleteBookById(id);
    }
}
