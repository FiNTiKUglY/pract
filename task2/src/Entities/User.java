import java.util.UUID;
import java.util.Date;

public class User {
    public UUID id;
    public String name;
    public String surname;
    public UUID roleId;
    public Date birthDate;

    public User() {

    }

    public User(UUID id, String name, String surname, 
                UUID roleId, Date birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.roleId = roleId;
        this.birthDate = birthDate;
    }
}
