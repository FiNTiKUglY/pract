package repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;
import entities.Role;

public class RoleRepository {
    private Connection connection;

    public RoleRepository(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<Role> getRoles() throws SQLException {
        ArrayList<Role> roles = new ArrayList<>();
        String query = "select * from roles";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                roles.add(new Role(UUID.fromString(rs.getString("id")),
                                    rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return roles;
    }

    public Role selectRoleById(UUID id) throws SQLException {
        String query = String.format("SELECT * FROM roles " +
                        "WHERE id = '%s'", id.toString());
        Role role = new Role();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                role = new Role(UUID.fromString(rs.getString("id")),
                                rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return role;
    }

    public void addRole(UUID id, String name) throws SQLException {
        String query = String.format("INSERT INTO roles " +
                        "VALUES ('%s', '%s')", id.toString(), name);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void removeRole(UUID id) throws SQLException {
        String query = String.format("DELETE FROM roles " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void updateRole(UUID id, String name) throws SQLException {
        String query = String.format("UPDATE roles " +
                        "SET name = '%s' " +
                        "WHERE id = '%s'", name, id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}