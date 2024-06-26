package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

    public Genre getGenreById(UUID id) throws NotFoundException {
        Optional<Genre> genreOpt = genreRepository.findById(id);
        if (genreOpt.isEmpty()) {
            throw new NotFoundException();
        }
        return genreOpt.get();
    }

    public void deleteGenreById(UUID id) throws NotFoundException {
        if (!genreRepository.existsById(id)) {
            throw new NotFoundException();
        }
        genreRepository.deleteById(id);
    }

    public Genre updateGenre(UUID id, Genre genre) {
        genre.setId(id);
        return genreRepository.save(genre);
    }
}
