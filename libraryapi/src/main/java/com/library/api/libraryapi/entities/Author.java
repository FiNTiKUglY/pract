package com.library.api.libraryapi.entities;

import java.util.UUID;

public class Author {
    private UUID id;
    private String name;
    private String surname;
    private String biography;

    public Author() {

    }

    public Author(UUID id, String name, String surname, 
                String biography) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }

    @Override
    public String toString() {
        return String.format("%s %s%n%s%nid: %s", surname, name, biography, id.toString());
    }
}
