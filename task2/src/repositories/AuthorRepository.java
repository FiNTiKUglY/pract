package repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import entities.*;

public class AuthorRepository implements BaseRepository {
    private Connection connection;
    Logger logger = Logger.getLogger(getClass().getName());

    public AuthorRepository(Connection connection) {
        this.connection = connection;
    }

    public List<IEntity> getAll() throws SQLException {
        ArrayList<IEntity> authors = new ArrayList<>();
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

    public IEntity getById(UUID id) throws SQLException {
        String query = String.format("select * from authors WHERE id = '%s'", id.toString());
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

    public void add(IEntity entity) throws SQLException {
        var author = (Author)entity;
        String query = String.format("INSERT INTO authors " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s')", 
                        author.getId().toString(), author.getName(), author.getSurname(), author.getBiography(), author.getImageLink());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void remove(UUID id) throws SQLException {
        String query = String.format("DELETE FROM authors WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void update(IEntity entity) throws SQLException {
        var author = (Author)entity;
        String query = String.format("UPDATE authors " +
                        "SET name = '%s', surname = '%s', biography = '%s', image_link = '%s' WHERE id = '%s'", 
                        author.getName(), author.getSurname(), author.getBiography(), author.getImageLink(), author.getId().toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
