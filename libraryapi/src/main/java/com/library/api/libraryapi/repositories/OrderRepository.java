package com.library.api.libraryapi.repositories;

import java.util.UUID;
import java.util.List;
import com.library.api.libraryapi.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findByUserId(UUID userId);
}
