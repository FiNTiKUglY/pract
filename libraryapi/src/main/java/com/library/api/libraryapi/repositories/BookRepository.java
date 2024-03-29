package com.library.api.libraryapi.repositories;

import java.util.List;
import java.util.UUID;
import com.library.api.libraryapi.entities.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findByAuthorId(UUID authorId);
}
