package entities;

import java.util.UUID;

public class Book {
    private UUID id;
    private String title;
    private UUID authorId;
    private String shortDescription;
    private Double cost;
    private String imageLink;
    private String downloadLink;

    public Book() {

    }

    public Book(UUID id, String title, UUID authorId, 
                    String shortDescription, Double cost,
                    String downloadLink, String imageLink) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.shortDescription = shortDescription;
        this.cost = cost;
        this.imageLink = imageLink;
        this.downloadLink = downloadLink;
    }

    @Override
    public String toString() {
        return String.format("%s: %s%ncost: %f%nauthor id: %s%nimage link: %s%ndownload link: %s%nid: %s", 
        title, shortDescription, cost, authorId, imageLink, downloadLink, id.toString());
    }
}
