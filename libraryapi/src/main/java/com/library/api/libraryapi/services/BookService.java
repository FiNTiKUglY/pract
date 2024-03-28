package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

import com.library.api.libraryapi.repositories.BookRepository;

@Service
public class BookService {
    
    @Autowired BookRepository bookRepository;

    public BookService() {

    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
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
