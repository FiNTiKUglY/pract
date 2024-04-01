package repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import entities.IEntity;
import entities.Review;

public class ReviewRepository implements BaseRepository {
    private Connection connection;
    Logger logger = Logger.getLogger(getClass().getName());

    public ReviewRepository(Connection connection) {
        this.connection = connection;
    }

    public List<IEntity> getAll() throws SQLException {
        List<IEntity> reviews = new ArrayList<>();
        String query = "select * from reviews";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                reviews.add(new Review(UUID.fromString(rs.getString("id")),
                                    rs.getInt("mark"),
                                    rs.getString("text"),
                                    UUID.fromString(rs.getString("user_id")),
                                    UUID.fromString(rs.getString("book_id"))));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return reviews;
    }

    public IEntity getById(UUID id) throws SQLException {
        String query = String.format("select * from reviews WHERE id = '%s'", id.toString());
        Review review = new Review();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                review = new Review(UUID.fromString(rs.getString("id")),
                                        rs.getInt("mark"),
                                        rs.getString("text"),
                                        UUID.fromString(rs.getString("user_id")),
                                        UUID.fromString(rs.getString("book_id")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return review;
    }

    public void add(IEntity entity) throws SQLException {
        var review = (Review)entity;
        String query = String.format("INSERT INTO reviews " +
                        "VALUES ('%s', %d, '%s', '%s', '%s')", 
                        review.getId().toString(), review.getMark(), review.getText(), 
                        review.getUserId().toString(), review.getBookId().toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void remove(UUID id) throws SQLException {
        String query = String.format("DELETE FROM reviews WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void update(IEntity entity) throws SQLException {
        var review = (Review)entity;
        String query = String.format("UPDATE reviews " +
                        "SET mark = %d, text = '%s', user_id = '%s', book_id = '%s' WHERE id = '%s'", 
                        review.getMark(), review.getText(), review.getUserId().toString(),
                        review.getBookId().toString(), review.getId().toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
