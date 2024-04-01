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
import entities.Order;

public class OrderRepository implements BaseRepository {
    private Connection connection;
    Logger logger = Logger.getLogger(getClass().getName());

    public OrderRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<IEntity> getAll() throws SQLException {
        List<IEntity> orders = new ArrayList<>();
        String query = "select * from orders";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                orders.add(new Order(UUID.fromString(rs.getString("id")),
                                    UUID.fromString(rs.getString("user_id")),
                                    rs.getString("adress"),
                                    rs.getBoolean("status")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return orders;
    }

    @Override
    public IEntity getById(UUID id) throws SQLException {
        String query = String.format("select * from orders WHERE id = '%s'", id.toString());
        Order order = new Order();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                order = new Order(UUID.fromString(rs.getString("id")),
                                    UUID.fromString(rs.getString("user_id")),
                                    rs.getString("adress"),
                                    rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return order;
    }

    @Override
    public void add(IEntity entity) throws SQLException {
        var order = (Order)entity;
        String query = String.format("INSERT INTO orders " +
                        "VALUES ('%s', '%s', '%s', %b)", 
                        order.getId().toString(), order.getUserId().toString(), order.getAddress(), order.getStatus());
        String query2 = "INSERT INTO books_orders VALUES ";
        for (UUID bookId : order.getBooks()) {
            query2 += String.format("('%s', '%s'), ", bookId, order.getId().toString());
        }
        query2 = query2.substring(0, query2.length() - 2);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
            stmt.executeUpdate(query2);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void remove(UUID id) throws SQLException {
        String query = String.format("DELETE FROM orders WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void update(IEntity entity) throws SQLException {
        var order = (Order)entity;
        String query = String.format("UPDATE orders " +
                        "SET bookId = '%s', userId = '%s', adress = '%s', status = %b WHERE id = '%s'", 
                        order.getUserId().toString(), order.getAddress(), order.getStatus(), order.getId().toString());
        String query2 = String.format("DELETE FROM books_orders WHERE order_id = '%s", order.getId().toString());
        String query3 = "INSERT INTO books_orders VALUES ";
        for (UUID bookId : order.getBooks()) {
            query3 += String.format("('%s', '%s'), ", bookId, order.getId().toString());
        }
        query3 = query3.substring(0, query3.length() - 2);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
            stmt.executeUpdate(query2);
            stmt.executeUpdate(query3);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
