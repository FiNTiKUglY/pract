package repositories;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import entities.Book;
import entities.IEntity;

public class BookRepository implements BaseRepository {
    private Connection connection;
    Logger logger = Logger.getLogger(getClass().getName());


    public BookRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<IEntity> getAll() throws SQLException {
        List<IEntity> books = new ArrayList<>();
        String query = "select * from books";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                books.add(new Book(UUID.fromString(rs.getString("id")),
                                    rs.getString("title"),
                                    UUID.fromString(rs.getString("author_id")),
                                    rs.getString("short_description"),
                                    rs.getDouble("cost"),
                                    rs.getString("image_link"),
                                    rs.getString("download_link")));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return books;
    }

    @Override
    public IEntity getById(UUID id) throws SQLException {
        String query = String.format("select * from books WHERE id = '%s'", id.toString());
        Book book = new Book();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                book = new Book(UUID.fromString(rs.getString("id")),
                                    rs.getString("title"),
                                    UUID.fromString(rs.getString("author_id")),
                                    rs.getString("description"),
                                    rs.getDouble("cost"),
                                    rs.getString("image_link"),
                                    rs.getString("download_link"));
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return book;
    }

    @Override
    public void add(IEntity entity) throws SQLException {
        var book = (Book)entity;
        String query = String.format("INSERT INTO books " +
                        "VALUES ('%s', '%s', '%s', '%s', %s, '%s', '%s')", 
                        book.getId().toString(), book.getTitle(), book.getAuthorId().toString(), book.getShortDescription(), 
                        book.getCost().toString().replace(',', '.'), book.getImageLink(), book.getDownloadLink(), book.getId().toString());
        String query2 = "INSERT INTO books_genres VALUES ";
        for (UUID genreId : book.getGenres()) {
            query2 += String.format("('%s', '%s'), ", book.getId().toString(), genreId);
        }
        query2 = query2.substring(0, query2.length() - 2);
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
            stmt.executeUpdate(query2);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void remove(UUID id) throws SQLException {
        String query = String.format("DELETE FROM books WHERE id = '%s'", id.toString());
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    @Override
    public void update(IEntity entity) throws SQLException {
        var book = (Book)entity;
        String query = String.format("UPDATE books " +
                        "SET title = '%s', author_id = '%s', short_description = '%s', cost = %s " +
                        "image_link = '%s', download_link = '%s' WHERE id = '%s'", 
                        book.getTitle(), book.getAuthorId().toString(), book.getShortDescription(), book.getCost().toString().replace(',', '.'), 
                        book.getImageLink(), book.getDownloadLink(), book.getId().toString());
        String query2 = String.format("DELETE FROM books_genres WHERE book_id = '%s", book.getId().toString());
        String query3 = "INSERT INTO books_genres VALUES ";
        for (UUID genreId : book.getGenres()) {
            query3 += String.format("('%s', '%s'), ", book.getId().toString(), genreId);
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
