package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

import com.library.api.libraryapi.repositories.AuthorRepository;

@Service
public class AuthorService {
    
    @Autowired AuthorRepository authorRepository;

    public AuthorService() {
        //Constructor for service
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author getAuthorById(UUID id) {
        return authorRepository.findById(id).get();
    }

    public void deleteAuthorById(UUID id) {
        authorRepository.deleteById(id);
    }

    public Author updateAuthor(UUID id, Author author) {
        author.setId(id);
        return authorRepository.save(author);
    }
}
