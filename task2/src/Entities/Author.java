import java.security.Timestamp;
import java.util.UUID;

public class Author {
    public UUID id;
    public String name;
    public String surname;
    public String biography;

    public Author(UUID id, String name, String surname, 
                String biography) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.biography = biography;
    }
}
