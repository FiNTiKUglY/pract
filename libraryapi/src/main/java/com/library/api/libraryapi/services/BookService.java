package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Book;
import com.library.api.libraryapi.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.Set;

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
        if (!book.getGenres().isEmpty()) {
            Set<Genre> genres = book.getGenres();
            for (Genre genre : genres) {
                Set<Book> books = genre.getBooks();
                if (books == null) {
                    books = new HashSet<>();
                }
                books.add(book);
                genre.setBooks(books);
            }
            book.setGenres(genres);
            bookRepository.save(book);
            for (Genre genre : genres) {
                genreRepository.save(genre);
            }
        }
        return book;
    }

    public Book getBookById(UUID id) {
        return bookRepository.findById(id).get();
    }

    public void deleteBookById(UUID id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(UUID id, Book book) {
        book.setId(id);
        return addBook(book);
    }
}
