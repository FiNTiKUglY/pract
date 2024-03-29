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

    @Override
    public String toString() {
        return String.format("%s,%n status: %b%nuser id: %s%nbook id: %s%nid: %s", adress, status, userId.toString(), bookId.toString(), id.toString());
    }
}
