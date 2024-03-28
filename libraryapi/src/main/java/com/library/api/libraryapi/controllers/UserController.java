package com.library.api.libraryapi.controllers;

import java.util.List;
import java.util.UUID;
import com.library.api.libraryapi.services.UserService;
import com.library.api.libraryapi.entities.User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;



@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/api/users/get")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/api/users/get/{id}")
    public User getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping("/api/users/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/api/users/update")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/api/users/delete/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);
    }
    
    
    @GetMapping("/")
    public String Test() {
        return "test";
    }
}
