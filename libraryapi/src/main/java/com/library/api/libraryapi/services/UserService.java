package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

import com.library.api.libraryapi.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired UserRepository userRepository;

    public UserService() {

    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).get();
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    public User updateUser(UUID id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }
}
