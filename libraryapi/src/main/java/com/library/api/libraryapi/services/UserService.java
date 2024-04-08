package com.library.api.libraryapi.services;

import com.library.api.libraryapi.entities.Role;
import com.library.api.libraryapi.entities.User;
import com.library.api.libraryapi.models.RegisterModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.UUID;

import java.util.Optional;

import com.library.api.libraryapi.repositories.UserRepository;
import com.library.api.libraryapi.repositories.RoleRepository;

@Service
public class UserService {
    
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired 
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(RegisterModel registerModel) throws NotFoundException {
        Optional<Role> roleOpt = roleRepository.findByName("user");
        if (roleOpt.isEmpty()) {
            throw new NotFoundException();
        }
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(registerModel.getEmail());
        user.setName(registerModel.getName());
        user.setSurname(registerModel.getSurname());
        user.setBirthDate(registerModel.getBirthDate());
        user.setPasswordHash(registerModel.getPassword());
        user.setRole(roleOpt.get());
        return userRepository.save(user);
    }

    public User getUserById(UUID id, String userName) throws AccessDeniedException, NotFoundException {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new NotFoundException();
        }
        User user = userOpt.get();
        if (!userName.equals(user.getId().toString())) {
            throw new AccessDeniedException("");
        }
        return user;
    }

    public User getUserByEmail(String email) throws NotFoundException {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            throw new NotFoundException();
        }
        return userOpt.get();
    }

    public User getUserByEmailAndPasswordHash(String email, String passwordHash) throws NotFoundException {
        Optional<User> userOpt = userRepository.findByEmailAndPasswordHash(email, passwordHash);
        if (userOpt.isEmpty()) {
            throw new NotFoundException();
        }
        return userOpt.get();
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
