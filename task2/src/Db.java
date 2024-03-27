import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import entities.*;

import java.util.Date;

public class Db {
    private Connection connection;

    public Db(Connection connection) {
        this.connection = connection;
    }

    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String query = "select * from users";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                users.add(new User(UUID.fromString(rs.getString("id")),
                                    rs.getString("name"),
                                    rs.getString("surname"),
                                    UUID.fromString(rs.getString("role_id")),
                                    rs.getDate("birth_date")));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
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
                                    UUID.fromString(rs.getString("role_id")),
                                    rs.getDate("birth_date"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return user;
    }

    public void addUser(UUID id, String name, String surname, UUID roleId, Date birthDate) throws SQLException {
        String query = String.format("INSERT INTO users " +
                        "VALUES ('%s', '%s', '%s', '%s', '%s')", id.toString(), name, surname, roleId.toString(), birthDate.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void removeUser(UUID id) throws SQLException {
        String query = String.format("DELETE FROM users " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void updateUser(UUID id, String name, String surname, UUID roleId, Date birthDate) throws SQLException {
        String query = String.format("UPDATE users " +
                        "SET name = '%s', surname = '%s', role_id = '%s', birth_date = '%s' " +
                        "WHERE id = '%s'", name, surname, roleId.toString(), birthDate.toString(), id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    /////////////////////////////////////////////////////////////

    public ArrayList<Role> getRoles() throws SQLException {
        ArrayList<Role> roles = new ArrayList<>();
        String query = "select * from roles";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                roles.add(new Role(UUID.fromString(rs.getString("id")),
                                    rs.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return roles;
    }

    public Role selectRoleById(UUID id) throws SQLException {
        String query = String.format("SELECT * FROM roles " +
                        "WHERE id = '%s'", id.toString());
        Role role = new Role();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                role = new Role(UUID.fromString(rs.getString("id")),
                                rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return role;
    }

    public void addRole(UUID id, String name) throws SQLException {
        String query = String.format("INSERT INTO roles " +
                        "VALUES ('%s', '%s')", id.toString(), name);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void removeRole(UUID id) throws SQLException {
        String query = String.format("DELETE FROM roles " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void updateRole(UUID id, String name) throws SQLException {
        String query = String.format("UPDATE roles " +
                        "SET name = '%s' " +
                        "WHERE id = '%s'", name, id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    /////////////////////////////////////////////////////////////

    public ArrayList<Author> getAuthors() throws SQLException {
        ArrayList<Author> authors = new ArrayList<>();
        String query = "select * from authors";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                authors.add(new Author(UUID.fromString(rs.getString("id")),
                                    rs.getString("name"),
                                    rs.getString("surname"),
                                    rs.getString("biography")));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
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
                                        rs.getString("biography"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return author;
    }

    public void addAuthor(UUID id, String name, String surname, String biography) throws SQLException {
        String query = String.format("INSERT INTO authors " +
                        "VALUES ('%s', '%s', '%s', '%s')", id.toString(), name, surname, biography);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void removeAuthor(UUID id) throws SQLException {
        String query = String.format("DELETE FROM authors " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void updateAuthor(UUID id, String name, String surname, String biography) throws SQLException {
        String query = String.format("UPDATE roles " +
                        "SET name = '%s', surname = '%s', biography = '%s' " +
                        "WHERE id = '%s'", name, surname, biography, id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    ////////////////////////////////////////////////////////////

    public ArrayList<Book> getBooks() throws SQLException {
        ArrayList<Book> books = new ArrayList<>();
        String query = "select * from books";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                books.add(new Book(UUID.fromString(rs.getString("id")),
                                    rs.getString("title"),
                                    UUID.fromString(rs.getString("author_id")),
                                    rs.getString("description"),
                                    rs.getDouble("cost")));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return books;
    }

    public Book selectBookById(UUID id) throws SQLException {
        String query = String.format("SELECT * FROM books " +
                        "WHERE id = '%s'", id.toString());
        Book book = new Book();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                book = new Book(UUID.fromString(rs.getString("id")),
                                    rs.getString("title"),
                                    UUID.fromString(rs.getString("author_id")),
                                    rs.getString("description"),
                                    rs.getDouble("cost"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return book;
    }

    public void addBook(UUID id, String title, UUID authorId, String shortDescription, Double cost) throws SQLException {
        String query = String.format("INSERT INTO books " +
                        "VALUES ('%s', '%s', '%s', '%s', %f)", id.toString(), title, authorId.toString(), shortDescription, cost);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void removeBook(UUID id) throws SQLException {
        String query = String.format("DELETE FROM books " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void updateBook(UUID id, String title, UUID authorId, String shortDescription, Double cost) throws SQLException {
        String query = String.format("UPDATE books " +
                        "SET title = '%s', author_id = '%s', short_description = '%s', cost = %f " +
                        "WHERE id = '%s'", title, authorId.toString(), shortDescription, cost, id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    ////////////////////////////////////////////////////////////

    public ArrayList<Review> getReviews() throws SQLException {
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
            System.out.println(e.toString());
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
            System.out.println(e.toString());
        }
        return review;
    }

    public void addReview(UUID id, int mark, String text, UUID userId, UUID bookId) throws SQLException {
        String query = String.format("INSERT INTO reviews " +
                        "VALUES ('%s', %d, '%s', '%s', '%s')", id.toString(), mark, text, userId.toString(), bookId.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void removeReview(UUID id) throws SQLException {
        String query = String.format("DELETE FROM reviews " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void updateReview(UUID id, int mark, String text, UUID userId, UUID bookId) throws SQLException {
        String query = String.format("UPDATE reviews " +
                        "SET mark = %d, text = '%s', user_id = '%s', book_id = '%s' " +
                        "WHERE id = '%s'", mark, text, userId.toString(), bookId.toString(), id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    ////////////////////////////////////////////////////////////

    public ArrayList<Order> getOrders() throws SQLException {
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
            System.out.println(e.toString());
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
            System.out.println(e.toString());
        }
        return order;
    }

    public void addOrder(UUID id, UUID bookId, UUID userId, String adress, boolean status) throws SQLException {
        String query = String.format("INSERT INTO orders " +
                        "VALUES ('%s', '%s', '%s', '%s', %b)", id.toString(), bookId.toString(), userId.toString(), adress, status);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void removeOrder(UUID id) throws SQLException {
        String query = String.format("DELETE FROM orders " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void updateOrder(UUID id, UUID bookId, UUID userId, String adress, boolean status) throws SQLException {
        String query = String.format("UPDATE orders " +
                        "SET bookId = '%s', userId = '%s', adress = '%s', status = %b " +
                        "WHERE id = '%s'", bookId.toString(), userId.toString(), adress, status, id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    ////////////////////////////////////////////////////////////

    public ArrayList<Genre> getGenres() throws SQLException {
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
            System.out.println(e.toString());
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
            System.out.println(e.toString());
        }
        return genre;
    }

    public void addGenre(UUID id, String name, String description) throws SQLException {
        String query = String.format("INSERT INTO orders " +
                        "VALUES ('%s', '%s', '%s')", id.toString(), name, description);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void removeGenre(UUID id) throws SQLException {
        String query = String.format("DELETE FROM genres " +
                        "WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public void updateGenre(UUID id, String name, String description) throws SQLException {
        String query = String.format("UPDATE genres " +
                        "SET name = '%s', description = '%s' " +
                        "WHERE id = '%s'", name, description, id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
