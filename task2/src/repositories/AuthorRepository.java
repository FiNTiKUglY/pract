package repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import entities.Author;

public class AuthorRepository {
    private Connection connection;
    Logger logger = Logger.getLogger(getClass().getName());

    public AuthorRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Author> getAuthors() throws SQLException {
        ArrayList<Author> authors = new ArrayList<>();
        String query = "select * from authors";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                authors.add(new Author(UUID.fromString(rs.getString("id")),
                                    rs.getString("name"),
                                    rs.getString("surname"),
                                    rs.getString("biography"),
                                    rs.getString("image_link")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
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
                                        rs.getString("biography"),
                                        rs.getString("image_link"));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return author;
    }

    public void addAuthor(UUID id, String name, String surname, String biography, String imageLink) throws SQLException {
        String query = String.format("INSERT INTO authors " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s')", id.toString(), name, surname, biography, imageLink);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void removeAuthor(UUID id) throws SQLException {
        String query = String.format("DELETE FROM authors " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void updateAuthor(UUID id, String name, String surname, String biography, String imageLink) throws SQLException {
        String query = String.format("UPDATE roles " +
                        "SET name = '%s', surname = '%s', biography = '%s', image_link = '%s' " +
                        "WHERE id = '%s'", name, surname, biography, imageLink, id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
