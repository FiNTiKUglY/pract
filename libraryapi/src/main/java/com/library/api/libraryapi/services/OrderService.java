package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Order;
import com.library.api.libraryapi.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.library.api.libraryapi.repositories.OrderRepository;
import com.library.api.libraryapi.repositories.UserRepository;

@Service
public class OrderService {
    
    OrderRepository orderRepository;
    UserRepository userRepository;

    @Autowired 
    public OrderService() {
        //Constructor for service
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getUserOrders(UUID userId, String userName) throws AccessDeniedException {
        if (!userName.equals(userId.toString())) {
            throw new AccessDeniedException("");
        }
        return orderRepository.findByUserId(userId);
    }

    public Order addOrder(Order order, String userName) {
        User user = new User();
        Optional<User> userOpt = userRepository.findById(UUID.fromString(userName));
        if (userOpt.isPresent()) {
            user = userOpt.get();
        }
        order.setUser(user);
        return orderRepository.save(order);
    }

    public Order getOrderById(UUID id, String userName) throws AccessDeniedException {
        Order order = new Order();
        Optional<Order> orderOpt = orderRepository.findById(id);
        if (orderOpt.isPresent()) {
            order = orderOpt.get();
        }
        if (!userName.equals(order.getUser().getId().toString())) {
            throw new AccessDeniedException("");
        }
        return order;
    }

    public void deleteOrderById(UUID id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrder(UUID id, Order order) {
        order.setId(id);
        return orderRepository.save(order);
    }
}
