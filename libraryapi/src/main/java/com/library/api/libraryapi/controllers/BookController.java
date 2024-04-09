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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;


@RequestMapping("/api/books")
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/author/{authorId}")
    public List<Book> getAuthorBooks(@PathVariable UUID authorId) {
        return bookService.getAuthorBooks(authorId);
    }

    @GetMapping("")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable UUID id) throws NotFoundException {
        return bookService.getBookById(id);
    }

    @GetMapping("/genre/{genreId}")
    public List<Book> getGenreBooks(@PathVariable UUID genreId) throws NotFoundException {
        return bookService.getGenreBooks(genreId);
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PostMapping("/update/{id}")
    public Book updateBook(@PathVariable UUID id, @RequestBody Book book) throws NotFoundException {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBook(@PathVariable UUID id) throws NotFoundException {
        bookService.deleteBookById(id);
    }
}
