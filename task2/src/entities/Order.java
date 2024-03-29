package entities;

import java.util.UUID;

public class Order {
    private UUID id;
    private UUID bookId;
    private UUID userId;
    private String adress;
    private boolean status;

    public Order() {}

    public Order(UUID id, UUID bookId, UUID userId, 
                    String adress, boolean status) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.adress = adress;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBookId() {
        return bookId;
    }

    public void setBookId(UUID bookId) {
        this.bookId = bookId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("%s,%n status: %b%nuser id: %s%nbook id: %s%nid: %s", adress, status, userId.toString(), bookId.toString(), id.toString());
    }
}
