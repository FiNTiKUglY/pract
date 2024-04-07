package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Role;
import com.library.api.libraryapi.entities.User;
import com.library.api.libraryapi.models.RegisterModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import com.library.api.libraryapi.repositories.UserRepository;
import com.library.api.libraryapi.repositories.RoleRepository;

@Service
public class UserService {
    
    @Autowired UserRepository userRepository;
    @Autowired RoleRepository roleRepository;

    public UserService() {
        //Constructor for service
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(RegisterModel registerModel) {
        Role role = roleRepository.findByName("user").get();
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(registerModel.getEmail());
        user.setName(registerModel.getName());
        user.setSurname(registerModel.getSurname());
        user.setBirthDate(registerModel.getBirthDate());
        user.setPasswordHash(registerModel.getPassword());
        user.setRole(role);
        return userRepository.save(user);
    }

    public User getUserById(UUID id, String userName) throws AccessDeniedException {
        var user = userRepository.findById(id).get();
        if (!userName.equals(user.getId().toString())) {
            throw new AccessDeniedException("");
        }
        return user;
    }

    public User getUserByEmail(String email) {
        User user = new User();
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            user = userOpt.get();
        }
        return user;
    }

    public User getUserByEmailAndPasswordHash(String email, String passwordHash) {
        User user = new User();
        Optional<User> userOpt = userRepository.findByEmailAndPasswordHash(email, passwordHash);
        if (userOpt.isPresent()) {
            user = userOpt.get();
        }
        return user;
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    public User updateUser(UUID id, User user, String userName) throws AccessDeniedException {
        if (!userName.equals(user.getId().toString())) {
            throw new AccessDeniedException("");
        }
        user.setId(id);
        return userRepository.save(user);
    }
}
