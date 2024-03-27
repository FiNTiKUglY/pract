import java.util.UUID;

public class Genre {
    public UUID id;
    public String name;
    public String description;

    public Genre() {
        
    }

    public Genre(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
