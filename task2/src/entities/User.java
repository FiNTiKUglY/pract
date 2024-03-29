package entities;

import java.util.UUID;
import java.util.Date;

public class User {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String passwordHash;
    private UUID roleId;
    private Date birthDate;

    public User() {}

    public User(UUID id, String name, String surname, 
                String email, String passwordHash,
                UUID roleId, Date birthDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passwordHash = passwordHash;
        this.roleId = roleId;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s, %s%nrole id: %s%npass hash: %s%nid: %s", 
        surname, name, email, birthDate, roleId.toString(), passwordHash, id.toString());
    }
}
