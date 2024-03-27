package com.library.api.libraryapi.entities;

import java.util.UUID;

public class Book {
    private UUID id;
    private String title;
    private UUID authorId;
    private String shortDescription;
    private Double cost;

    public Book() {

    }

    public Book(UUID id, String title, UUID authorId, 
                    String shortDescription, Double cost) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.shortDescription = shortDescription;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return String.format("%s: %s%ncost: %f%nauthor id: %s%nid: %s", title, shortDescription, cost, authorId, id.toString());
    }
}
