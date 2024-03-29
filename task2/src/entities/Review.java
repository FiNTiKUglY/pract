package entities;

import java.util.UUID;

public class Review implements IEntity {
    private UUID id;
    private int mark;
    private String text;
    private UUID userId;
    private UUID bookId;

    public Review() {}

    public Review(UUID id, int mark, String text, 
                    UUID userId, UUID bookId) {
        this.id = id;
        this.mark = mark;
        this.text = text;
        this.userId = userId;
        this.bookId = bookId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return String.format("%s,%n mark: %d%nuser id: %s%nbook id: %s%nid: %s", text, mark, userId.toString(), bookId.toString(), id.toString());
    }
}
