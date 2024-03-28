package repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;
import entities.Book;

public class BookRepository {
    private Connection connection;

    public BookRepository(Connection connection) {
        this.connection = connection;
    }

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
}
