package com.library.api.libraryapi.entities;

import java.util.UUID;

public class Review {
    private UUID id;
    private int mark;
    private String text;
    private UUID userId;
    private UUID bookId;

    public Review() {

    }

    public Review(UUID id, int mark, String text, 
                    UUID userId, UUID bookId) {
        this.id = id;
        this.mark = mark;
        this.text = text;
        this.userId = userId;
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return String.format("%s,%n mark: %d%nuser id: %s%nbook id: %s%nid: %s", text, mark, userId.toString(), bookId.toString(), id.toString());
    }
}
