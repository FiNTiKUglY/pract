import java.util.UUID;

public class User {
    public UUID id;
    public String name;
    public String surname;
    public UUID roleId;

    public User() {

    }

    public User(UUID id, String name, String surname, 
                UUID roleId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.roleId = roleId;
    }
}
