package repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import entities.Review;

public class ReviewRepository {
    private Connection connection;
    Logger logger = Logger.getLogger(getClass().getName());

    public ReviewRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Review> getReviews() throws SQLException {
        ArrayList<Review> reviews = new ArrayList<>();
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

    public Review selectReviewById(UUID id) throws SQLException {
        String query = String.format("SELECT * FROM reviews " +
                        "WHERE id = '%s'", id.toString());
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

    public void addReview(UUID id, int mark, String text, UUID userId, UUID bookId) throws SQLException {
        String query = String.format("INSERT INTO reviews " +
                        "VALUES ('%s', %d, '%s', '%s', '%s')", id.toString(), mark, text, userId.toString(), bookId.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void removeReview(UUID id) throws SQLException {
        String query = String.format("DELETE FROM reviews " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void updateReview(UUID id, int mark, String text, UUID userId, UUID bookId) throws SQLException {
        String query = String.format("UPDATE reviews " +
                        "SET mark = %d, text = '%s', user_id = '%s', book_id = '%s' " +
                        "WHERE id = '%s'", mark, text, userId.toString(), bookId.toString(), id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
