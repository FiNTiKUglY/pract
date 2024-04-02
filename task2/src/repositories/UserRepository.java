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
import entities.User;

public class UserRepository implements BaseRepository {
    private Connection connection;
    Logger logger = Logger.getLogger(getClass().getName());

    public UserRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<IEntity> getAll() throws SQLException {
        List<IEntity> users = new ArrayList<>();
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

    @Override
    public IEntity getById(UUID id) throws SQLException {
        String query = String.format("select * from users WHERE id = '%s'", id.toString());
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

    @Override
    public void add(IEntity entity) throws SQLException {
        var user = (User)entity;
        String query = String.format("INSERT INTO users " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s')", 
                        user.getId().toString(), user.getName(), user.getSurname(), user.getEmail(), 
                        user.getPasswordHash(), user.getRoleId().toString(), user.getBirthDate().toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void remove(UUID id) throws SQLException {
        String query = String.format("DELETE FROM users WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void update(IEntity entity) throws SQLException {
        var user = (User)entity;
        String query = String.format("UPDATE users " +
                        "SET name = '%s', surname = '%s', role_id = '%s', birth_date = '%s', " +
                        "email = '%s', password_hash = '%s' WHERE id = '%s'", 
                        user.getName(), user.getSurname(), user.getEmail(), user.getPasswordHash(),
                        user.getRoleId().toString(), user.getBirthDate().toString(), user.getId().toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }
}
