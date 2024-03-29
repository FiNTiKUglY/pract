package entities;

import java.util.UUID;

public class Author {
    private UUID id;
    private String name;
    private String surname;
    private String biography;
    private String imageLink;

    public Author() {

    }

    public Author(UUID id, String name, String surname, 
                String biography, String imageLink) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.biography = biography;
        this.imageLink = imageLink;
    }

    @Override
    public String toString() {
        return String.format("%s %s%n%s%nimage_link: %s%nid: %s", surname, name, biography, imageLink, id.toString());
    }
}
