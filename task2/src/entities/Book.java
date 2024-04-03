package entities;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Book implements IEntity {
    private UUID id;
    private String title;
    private UUID authorId;
    private String shortDescription;
    private double cost;
    private String imageLink;
    private String downloadLink;
    private List<UUID> genresIds;

    public Book() {
        this.genresIds = new ArrayList<>();
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
        this.genresIds = new ArrayList<>();
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

    public void addGenre(UUID genreId) {
        this.genresIds.add(genreId);
    }

    public void removeGenre(UUID genreId) {
        this.genresIds.remove(genreId);
    }

    public List<UUID> getGenres() {
        return genresIds;
    }

    @Override
    public String toString() {
        return String.format("%s: %s%ncost: %f%nauthor id: %s%nimage link: %s%ndownload link: %s%nid: %s", 
        title, shortDescription, cost, authorId, imageLink, downloadLink, id.toString());
    }
}