package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

import com.library.api.libraryapi.repositories.OrderRepository;

@Service
public class OrderService {
    
    @Autowired OrderRepository orderRepository;

    public OrderService() {
        //Constructor for service
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order getOrderById(UUID id) {
        return orderRepository.findById(id).get();
    }

    public void deleteOrderById(UUID id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrder(UUID id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }
}
