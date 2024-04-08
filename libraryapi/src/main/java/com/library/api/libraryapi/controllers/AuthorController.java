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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;


@RequestMapping("/api/authors")
@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @GetMapping("")
    public List<Author> getAuthors() {
        return authorService.getAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable UUID id) throws NotFoundException {
        return authorService.getAuthorById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/add")
    public Author addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/update/{id}")
    public Author updateAuthor(@PathVariable UUID id, @RequestBody Author author) {
        return authorService.updateAuthor(id, author);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/delete/{id}")
    public void deleteAuthor(@PathVariable UUID id) {
        authorService.deleteAuthorById(id);
    }
}
