package com.library.api.libraryapi.controllers;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;


@RequestMapping("/api/users")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable UUID id, Principal principal) throws AccessDeniedException, NotFoundException {
        return userService.getUserById(id, principal.getName());
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/update/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user, Principal principal) throws AccessDeniedException {
        return userService.updateUser(id, user, principal.getName());
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable UUID id) throws NotFoundException {
        userService.deleteUserById(id);
    }
}
