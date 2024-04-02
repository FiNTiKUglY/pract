package com.library.api.libraryapi.controllers;

import com.library.api.libraryapi.models.RegisterModel;
import com.library.api.libraryapi.models.LoginModel;
import com.library.api.libraryapi.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/api/auth/test")
    public String test() {
        return "test";
    }

    @PostMapping("/api/auth/signin")
    public ResponseEntity<Object> signIn(@RequestBody LoginModel loginModel) {
        var authenticationResponse = authenticationService.signIn(loginModel.getEmail(), loginModel.getPassword());

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @PostMapping("/api/auth/signup")
    public ResponseEntity<Object> signUp(@RequestBody RegisterModel registerModel) {
        var authenticationResponse = authenticationService.signUp(registerModel);

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }
}