package repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;
import entities.Author;

public class AuthorRepository {
    private Connection connection;

    public AuthorRepository(Connection connection) {
        this.connection = connection;
    }

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

    public Author selectAthorById(UUID id) throws SQLException {
        String query = String.format("SELECT * FROM authors " +
                        "WHERE id = '%s'", id.toString());
        Author author = new Author();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                author = new Author(UUID.fromString(rs.getString("id")),
                                        rs.getString("name"),
                                        rs.getString("surname"),
                                        rs.getString("biography"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return author;
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

    public void updateAuthor(UUID id, String name, String surname, String biography) throws SQLException {
        String query = String.format("UPDATE roles " +
                        "SET name = '%s', surname = '%s', biography = '%s' " +
                        "WHERE id = '%s'", name, surname, biography, id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}