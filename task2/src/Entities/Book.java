import java.util.UUID;

public class Book {
    public UUID id;
    public String title;
    public UUID authorId;
    public String shortDescription;
    public Double cost;

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
}
