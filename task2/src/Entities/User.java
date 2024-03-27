package entities;

import java.util.UUID;
import java.util.Date;

public class User {
    private UUID id;
    private String name;
    private String surname;
    private UUID roleId;
    private Date birthDate;

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

    @Override
    public String toString() {
        return String.format("%s %s, %s : %s", surname, name, birthDate, id.toString());
    }
}
