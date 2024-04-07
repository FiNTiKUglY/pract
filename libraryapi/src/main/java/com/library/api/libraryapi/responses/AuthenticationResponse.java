package com.library.api.libraryapi.responses;

import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class AuthenticationResponse {
    private UUID id;
    private String token;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}