package entities;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class Order implements IEntity {
    private UUID id;
    private UUID userId;
    private String address;
    private boolean status;
    private List<UUID> booksIds;

    public Order() {
        this.booksIds = new ArrayList<>();
    }

    public Order(UUID id, UUID userId, 
                    String address, boolean status) {
        this.id = id;
        this.userId = userId;
        this.address = address;
        this.status = status;
        this.booksIds = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void addBook(UUID bookId) {
        this.booksIds.add(bookId);
    }

    public void removeBook(UUID bookId) {
        this.booksIds.remove(bookId);
    }

    public List<UUID> getBooks() {
        return booksIds;
    }

    @Override
    public String toString() {
        return String.format("%s,%n status: %b%nuser id: %s%nid: %s", address, status, userId.toString(), id.toString());
    }
}
