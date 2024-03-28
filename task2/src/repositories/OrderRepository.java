package repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import entities.Order;

public class OrderRepository {
    private Connection connection;
    Logger logger = Logger.getLogger(getClass().getName());

    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Order> getOrders() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        String query = "select * from orders";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                orders.add(new Order(UUID.fromString(rs.getString("id")),
                                    UUID.fromString(rs.getString("book_id")),
                                    UUID.fromString(rs.getString("user_id")),
                                    rs.getString("adress"),
                                    rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return orders;
    }

    public Order selectOrderById(UUID id) throws SQLException {
        String query = String.format("SELECT * FROM orders " +
                        "WHERE id = '%s'", id.toString());
        Order order = new Order();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                order = new Order(UUID.fromString(rs.getString("id")),
                                    UUID.fromString(rs.getString("book_id")),
                                    UUID.fromString(rs.getString("user_id")),
                                    rs.getString("adress"),
                                    rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return order;
    }

    public void addOrder(UUID id, UUID bookId, UUID userId, String adress, boolean status) throws SQLException {
        String query = String.format("INSERT INTO orders " +
                        "VALUES ('%s', '%s', '%s', '%s', %b)", id.toString(), bookId.toString(), userId.toString(), adress, status);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void removeOrder(UUID id) throws SQLException {
        String query = String.format("DELETE FROM orders " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void updateOrder(UUID id, UUID bookId, UUID userId, String adress, boolean status) throws SQLException {
        String query = String.format("UPDATE orders " +
                        "SET bookId = '%s', userId = '%s', adress = '%s', status = %b " +
                        "WHERE id = '%s'", bookId.toString(), userId.toString(), adress, status, id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
