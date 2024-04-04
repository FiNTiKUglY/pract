package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Book;
import com.library.api.libraryapi.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import com.library.api.libraryapi.repositories.BookRepository;
import com.library.api.libraryapi.repositories.GenreRepository;

@Service
public class BookService {
    
    @Autowired BookRepository bookRepository;
    @Autowired GenreRepository genreRepository;

    public BookService() {
        //Constructor for service
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAuthorBooks(UUID authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public List<Book> getGenreBooks(UUID genreId) {
        List<Genre> genres = new ArrayList<>();
        genres.add(genreRepository.findById(genreId).get());
        return bookRepository.findByGenresContains(genres);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book getBookById(UUID id) {
        return bookRepository.findById(id).get();
    }

    public void deleteBookById(UUID id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(UUID id, Book book) {
        book.setId(id);
        return bookRepository.save(book);
    }
}
