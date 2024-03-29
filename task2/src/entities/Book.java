package entities;

import java.util.UUID;

public class Book implements IEntity {
    private UUID id;
    private String title;
    private UUID authorId;
    private String shortDescription;
    private Double cost;
    private String imageLink;
    private String downloadLink;

    public Book() {}

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public void setAuthorId(UUID authorId) {
        this.authorId = authorId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    @Override
    public String toString() {
        return String.format("%s: %s%ncost: %f%nauthor id: %s%nimage link: %s%ndownload link: %s%nid: %s", 
        title, shortDescription, cost, authorId, imageLink, downloadLink, id.toString());
    }
}
