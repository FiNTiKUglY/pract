package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Role;
import com.library.api.libraryapi.entities.User;
import com.library.api.libraryapi.models.RegisterModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

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

    public User getUserById(UUID id) {
        return userRepository.findById(id).get();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public User getUserByEmailAndPasswordHash(String email, String passwordHash) {
        return userRepository.findByEmailAndPasswordHash(email, passwordHash).get();
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    public User updateUser(UUID id, User user) {
        user.setId(id);
        return userRepository.save(user);
    }
}
