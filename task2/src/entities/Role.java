package entities;

import java.util.UUID;

public class Role implements IEntity {
    private UUID id;
    private String name;

    public Role() {}

    public Role(UUID id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("%s : %s", name, id.toString());
    }
}
