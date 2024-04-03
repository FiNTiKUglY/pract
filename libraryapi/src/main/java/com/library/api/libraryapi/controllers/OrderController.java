package com.library.api.libraryapi.controllers;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import com.library.api.libraryapi.services.OrderService;
import com.library.api.libraryapi.entities.Order;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/api/orders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/users/{userId}/orders")
    public List<Order> getUserOrders(@PathVariable UUID userId, Principal principal) throws AccessDeniedException {
        return orderService.getUserOrders(userId, principal.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/api/orders/{id}")
    public Order getOrderById(@PathVariable UUID id, Principal principal) throws AccessDeniedException {
        return orderService.getOrderById(id, principal.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/api/orders/add")
    public Order addOrder(@RequestBody Order order, Principal principal) {
        return orderService.addOrder(order, principal.getName());
    }

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/api/orders/update/{id}")
    public Order updateOrder(@PathVariable UUID id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/api/orders/delete/{id}")
    public void deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrderById(id);
    }
}
