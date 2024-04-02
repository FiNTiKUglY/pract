package com.library.api.libraryapi.controllers;

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


@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/api/orders")
    public List<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/api/users/{userId}/orders")
    public List<Order> getUserOrders(@PathVariable UUID userId) {
        return orderService.getUserOrders(userId);
    }

    @GetMapping("/api/orders/{id}")
    public Order getOrderById(@PathVariable UUID id) {
        return orderService.getOrderById(id);
    }

    @PostMapping("/api/orders/add")
    public Order addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @PostMapping("/api/orders/update/{id}")
    public Order updateOrder(@PathVariable UUID id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/api/orders/delete/{id}")
    public void deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrderById(id);
    }
}
