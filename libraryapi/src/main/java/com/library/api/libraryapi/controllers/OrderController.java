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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;


@RequestMapping("/api/orders")
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("user/{userId}")
    public List<Order> getUserOrders(@PathVariable UUID userId, Principal principal) throws AccessDeniedException {
        return orderService.getUserOrders(userId, principal.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable UUID id, Principal principal) throws AccessDeniedException, NotFoundException {
        return orderService.getOrderById(id, principal.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add")
    public Order addOrder(@RequestBody Order order, Principal principal) throws NotFoundException {
        return orderService.addOrder(order, principal.getName());
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/update/{id}")
    public Order updateOrder(@PathVariable UUID id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/delete/{id}")
    public void deleteOrder(@PathVariable UUID id) throws NotFoundException {
        orderService.deleteOrderById(id);
    }
}
