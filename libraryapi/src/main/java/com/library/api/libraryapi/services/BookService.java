package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Book;
import com.library.api.libraryapi.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Set;

import com.library.api.libraryapi.repositories.BookRepository;
import com.library.api.libraryapi.repositories.GenreRepository;

@Service
public class BookService {
    
    BookRepository bookRepository;
    GenreRepository genreRepository;

    @Autowired 
    public BookService(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getAuthorBooks(UUID authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    public List<Book> getGenreBooks(UUID genreId) throws NotFoundException {
        List<Genre> genres = new ArrayList<>();
        Optional<Genre> genreOpt = genreRepository.findById(genreId);
        if (genreOpt.isEmpty()) {
            throw new NotFoundException();
        }
        Genre genre = genreOpt.get();
        genres.add(genre);
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
            genreRepository.saveAll(genres);
        }
        return book;
    }

    public Book getBookById(UUID id) throws NotFoundException {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            throw new NotFoundException();
        }
        return bookOpt.get();
    }

    public void deleteBookById(UUID id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(UUID id, Book book) throws NotFoundException {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isEmpty()) {
            throw new NotFoundException();
        }
        Book oldBook = bookOpt.get();
        book.setId(id);
        for (Genre genre : oldBook.getGenres()) {
            var books = genre.getBooks();
            books.remove(oldBook);
            genre.setBooks(books);
            genreRepository.save(genre);
        }
        return addBook(book);
    }
}
