package com.library.api.libraryapi.services;

import com.library.api.libraryapi.security.JwtUtil;
import com.library.api.libraryapi.models.RegisterModel;
import com.library.api.libraryapi.responses.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthenticationService(UserService userService,
                                 JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponse signIn(String email, String passwordHash) throws NotFoundException {
        var user = userService.getUserByEmailAndPasswordHash(email, passwordHash);
        var token = jwtUtil.generateToken(user.getId());

        return new AuthenticationResponse(user.getId(), token);
    }

    public AuthenticationResponse signUp(RegisterModel registerModel) throws NotFoundException {
        var user = userService.addUser(registerModel);
        var token = jwtUtil.generateToken(user.getId());

        return new AuthenticationResponse(user.getId(), token);
    }
}