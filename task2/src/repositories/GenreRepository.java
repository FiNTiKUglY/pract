package repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import entities.Genre;
import entities.IEntity;

public class GenreRepository implements BaseRepository {
    private Connection connection;
    Logger logger = Logger.getLogger(getClass().getName());


    public GenreRepository(Connection connection) {
        this.connection = connection;
    }

    public List<IEntity> getAll() throws SQLException {
        ArrayList<IEntity> genres = new ArrayList<>();
        String query = "select * from genres";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                genres.add(new Genre(UUID.fromString(rs.getString("id")),
                                    rs.getString("name"),
                                    rs.getString("description")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return genres;
    }

    public IEntity getById(UUID id) throws SQLException {
        String query = String.format("select * from genres WHERE id = '%s'", id.toString());
        Genre genre = new Genre();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                genre = new Genre(UUID.fromString(rs.getString("id")),
                                    rs.getString("name"),
                                    rs.getString("description"));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return genre;
    }

    public void add(IEntity entity) throws SQLException {
        var genre = (Genre)entity;
        String query = String.format("INSERT INTO orders " +
                        "VALUES ('%s', '%s', '%s')", 
                        genre.getId().toString(), genre.getName(), genre.getDescription());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void remove(UUID id) throws SQLException {
        String query = String.format("DELETE FROM genres WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void update(IEntity entity) throws SQLException {
        var genre = (Genre)entity;
        String query = String.format("UPDATE genres " +
                        "SET name = '%s', description = '%s' WHERE id = '%s'", 
                        genre.getName(), genre.getDescription(), genre.getId().toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
