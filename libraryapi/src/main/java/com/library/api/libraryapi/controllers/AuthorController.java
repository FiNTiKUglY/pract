package com.library.api.libraryapi.controllers;

import java.util.List;
import java.util.UUID;
import com.library.api.libraryapi.services.AuthorService;
import com.library.api.libraryapi.entities.Author;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("/api/authors")
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping("/api/authors/{id}")
    public Author getAuthorById(@PathVariable UUID id) {
        return authorService.getAuthorById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/api/authors/add")
    public Author addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/api/authors/update/{id}")
    public Author updateAuthor(@PathVariable UUID id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/api/authors/delete/{id}")
    public void deleteAuthor(@PathVariable UUID id) {
        authorService.deleteAuthorById(id);
    }
}
