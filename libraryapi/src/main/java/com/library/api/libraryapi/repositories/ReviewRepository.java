package com.library.api.libraryapi.repositories;

import java.util.UUID;
import com.library.api.libraryapi.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {

}
