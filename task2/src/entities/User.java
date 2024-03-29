package entities;

import java.util.UUID;
import java.util.Date;

public class User implements IEntity {
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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UUID getRoleId() {
        return roleId;
    }

    public void setRoleId(UUID roleId) {
        this.roleId = roleId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s, %s%nrole id: %s%npass hash: %s%nid: %s", 
        surname, name, email, birthDate, roleId.toString(), passwordHash, id.toString());
    }
}
