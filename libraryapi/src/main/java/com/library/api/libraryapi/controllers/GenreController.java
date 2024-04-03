package com.library.api.libraryapi.controllers;

import java.util.List;
import java.util.UUID;
import com.library.api.libraryapi.services.GenreService;
import com.library.api.libraryapi.entities.Genre;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping("/api/genres")
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    @GetMapping("/api/genres/{id}")
    public Genre getGenreById(@PathVariable UUID id) {
        return genreService.getGenreById(id);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/api/genres/add")
    public Genre addGenre(@RequestBody Genre genre) {
        return genreService.addGenre(genre);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/api/genres/update/{id}")
    public Genre updateGenre(@PathVariable UUID id, @RequestBody Genre genre) {
        return genreService.updateGenre(id, genre);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/api/genres/delete/{id}")
    public void deleteGenre(@PathVariable UUID id) {
        genreService.deleteGenreById(id);
    }
}
