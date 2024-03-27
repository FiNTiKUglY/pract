package com.library.api.libraryapi.entities;

import java.util.UUID;

public class Role {
    private UUID id;
    private String name;

    public Role() {

    }

    public Role(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s : %s", name, id.toString());
    }
}
