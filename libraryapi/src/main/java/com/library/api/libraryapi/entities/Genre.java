package com.library.api.libraryapi.entities;

import java.util.UUID;

public class Genre {
    private UUID id;
    private String name;
    private String description;

    public Genre() {
        
    }

    public Genre(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("%s: %s%nid: %s", name, description, id.toString());
    }
}
