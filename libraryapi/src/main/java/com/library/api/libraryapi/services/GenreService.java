package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

import com.library.api.libraryapi.repositories.GenreRepository;

@Service
public class GenreService {
    
    @Autowired GenreRepository genreRepository;

    public GenreService() {
        //Constructor for service
    }

    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre getGenreById(UUID id) {
        return genreRepository.findById(id).get();
    }

    public void deleteGenreById(UUID id) {
        genreRepository.deleteById(id);
    }

    public Genre updateGenre(UUID id, Genre genre) {
        genre.setId(id);
        return genreRepository.save(genre);
    }
}
