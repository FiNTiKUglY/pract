import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;
import Entities.*;

public class Db {
    private Connection connection;

    public Db(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String query = "select * from users";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                users.add(new User(UUID.fromString(rs.getString("id")),
                                    rs.getString("name"),
                                    rs.getString("surname"),
                                    UUID.fromString(rs.getString("role_id"))));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return users;
    }

    public void addUser(UUID id, String name, String surname, UUID roleId) throws SQLException {
        String query = String.format("INSERT INTO users " +
                        "VALUES ('%s', '%s', '%s', '%s')", id.toString(), name, surname, roleId.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void removeUser(UUID id) throws SQLException {
        String query = String.format("DELETE FROM users " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    /////////////////////////////////////////////////////////////

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

    /////////////////////////////////////////////////////////////

    public ArrayList<Author> getAuthors() throws SQLException {
        ArrayList<Author> authors = new ArrayList<>();
        String query = "select * from authors";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                authors.add(new Author(UUID.fromString(rs.getString("id")),
                                    rs.getString("name"),
                                    rs.getString("surname"),
                                    rs.getString("biography")));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return authors;
    }

    public void addAuthor(UUID id, String name, String surname, String biography) throws SQLException {
        String query = String.format("INSERT INTO authors " +
                        "VALUES ('%s', '%s', '%s', '%s')", id.toString(), name, surname, biography);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void removeAuthor(UUID id) throws SQLException {
        String query = String.format("DELETE FROM authors " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
