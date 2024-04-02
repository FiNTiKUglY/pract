package com.library.api.libraryapi.repositories;

import java.util.UUID;
import java.util.Optional;

import com.library.api.libraryapi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPasswordHash(String email, String passwordHash);
}
