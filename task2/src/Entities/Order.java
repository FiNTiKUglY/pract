import java.util.UUID;

public class Order {
    public UUID id;
    public UUID bookId;
    public UUID userId;
    public String adress;
    public boolean status;

    public Order() {

    }

    public Order(UUID id, UUID bookId, UUID userId, 
                    String adress, boolean status) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.adress = adress;
        this.status = status;
    }
}
