package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.library.api.libraryapi.repositories.GenreRepository;

@Service
public class GenreService {
    
    GenreRepository genreRepository;

    @Autowired 
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    public Genre addGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    public Genre getGenreById(UUID id) {
        Genre genre = new Genre();
        Optional<Genre> genreOpt = genreRepository.findById(id);
        if (genreOpt.isPresent()) {
            genre = genreOpt.get();
        }
        return genre;
    }

    public void deleteGenreById(UUID id) {
        genreRepository.deleteById(id);
    }

    public Genre updateGenre(UUID id, Genre genre) {
        genre.setId(id);
        return genreRepository.save(genre);
    }
}
