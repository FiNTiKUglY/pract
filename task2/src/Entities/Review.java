import java.util.UUID;

public class Review {
    public UUID id;
    public int mark;
    public String text;
    public UUID userId;
    public UUID bookId;

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
}
