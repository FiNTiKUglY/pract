package repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

import entities.User;

public class UserRepository {
    private Connection connection;
    Logger logger = Logger.getLogger(getClass().getName());

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    public List<User> getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String query = "select * from users";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                users.add(new User(UUID.fromString(rs.getString("id")),
                                    rs.getString("name"),
                                    rs.getString("surname"),
                                    rs.getString("email"),
                                    rs.getString("password_hash"),
                                    UUID.fromString(rs.getString("role_id")),
                                    rs.getDate("birth_date")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return users;
    }

    public User selectUserById(UUID id) throws SQLException {
        String query = String.format("SELECT * FROM users " +
                        "WHERE id = '%s'", id.toString());
        User user = new User();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                user = new User(UUID.fromString(rs.getString("id")),
                                    rs.getString("name"),
                                    rs.getString("surname"),
                                    rs.getString("email"),
                                    rs.getString("password_hash"),
                                    UUID.fromString(rs.getString("role_id")),
                                    rs.getDate("birth_date"));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return user;
    }

    public void addUser(UUID id, String name, String surname, UUID roleId, Date birthDate, String email, String passwordHash) throws SQLException {
        String query = String.format("INSERT INTO users " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", 
                        id.toString(), name, surname, email, passwordHash, roleId.toString(), birthDate.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void removeUser(UUID id) throws SQLException {
        String query = String.format("DELETE FROM users " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    public void updateUser(UUID id, String name, String surname, UUID roleId, Date birthDate, String email, String passwordHash) throws SQLException {
        String query = String.format("UPDATE users " +
                        "SET name = '%s', surname = '%s', role_id = '%s', birth_date = '%s' " +
                        "email = '%s', password_hash = '%s' " +
                        "WHERE id = '%s'", 
                        name, surname, roleId.toString(), birthDate.toString(), email, passwordHash, id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
