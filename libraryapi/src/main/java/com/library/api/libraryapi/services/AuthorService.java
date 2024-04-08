package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import com.library.api.libraryapi.repositories.AuthorRepository;

@Service
public class AuthorService {
    
    AuthorRepository authorRepository;

    @Autowired 
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author getAuthorById(UUID id) throws NotFoundException {
        Optional<Author> authorOpt = authorRepository.findById(id);
        if (authorOpt.isEmpty()) {
            throw new NotFoundException();
        }
        return authorOpt.get();
    }

    public void deleteAuthorById(UUID id) {
        authorRepository.deleteById(id);
    }

    public Author updateAuthor(UUID id, Author author) {
        author.setId(id);
        return authorRepository.save(author);
    }
}
