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

public class GenreRepository {
    private Connection connection;
    Logger logger = Logger.getLogger(getClass().getName());


    public GenreRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Genre> getGenres() throws SQLException {
        ArrayList<Genre> genres = new ArrayList<>();
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

    public Genre selectGenreById(UUID id) throws SQLException {
        String query = String.format("SELECT * FROM genres " +
                        "WHERE id = '%s'", id.toString());
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

    public void addGenre(UUID id, String name, String description) throws SQLException {
        String query = String.format("INSERT INTO orders " +
                        "VALUES ('%s', '%s', '%s')", id.toString(), name, description);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void removeGenre(UUID id) throws SQLException {
        String query = String.format("DELETE FROM genres " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void updateGenre(UUID id, String name, String description) throws SQLException {
        String query = String.format("UPDATE genres " +
                        "SET name = '%s', description = '%s' " +
                        "WHERE id = '%s'", name, description, id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
