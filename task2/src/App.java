import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;

import config.DbConfig;
import repositories.*;
import entities.*;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());
    private static final Scanner in = new Scanner(System.in);

    public static Connection connect() throws SQLException {
        
        try {
            var jdbcUrl = DbConfig.getDbUrl();
            var user = DbConfig.getDbUsername();
            var password = DbConfig.getDbPassword();

            return DriverManager.getConnection(jdbcUrl, user, password);

        } catch (SQLException  e) {
            logger.warning(e.getMessage());
            return null;
        }
    }

    public static void drawMenu(BaseRepository repository, IEntity entity) throws Exception {
        logger.info("Choose action:");
        logger.info("1. Add");
        logger.info("2. Update");
        logger.info("3. Remove");
        logger.info("4. Show");
        int chosenNumber = in.nextInt();
        switch (chosenNumber) {
            case 1:
                var fields = entity.getClass().getDeclaredFields();
                for (var field : fields) {
                    field.setAccessible(true);
                    logger.info("Type " + field.getName());
                    if (field.getType() == boolean.class)  {
                        var value = in.nextBoolean();
                        field.set(entity, value);
                    }
                    else if (field.getType() == int.class) {
                        var value = in.nextInt();
                        field.set(entity, value);
                    } 
                    else if (field.getType() == double.class) {
                        var value = in.nextDouble();
                        field.set(entity, value);
                    }
                    else if (field.getType() == UUID.class) {
                        var value = UUID.fromString(in.next());
                        field.set(entity, value);
                    }
                    else if (field.getType() == Date.class) {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                        var strValue = in.next();
                        var value = formatter.parse(strValue);
                        field.set(entity, value);
                    }
                    else {
                        var value = in.next();
                        field.set(entity, value);
                    }
                }
                repository.add(entity);
                break;
            case 2:
                var listEntities = repository.getAll();
                for (int i = 0; i < listEntities.size(); i++) {
                    logger.info(String.format("%d. %s%n", i+1, listEntities.get(i).toString()));
                }
                logger.info("\nType id for update");
                var id = UUID.fromString(in.next());
                entity = repository.getById(id);
                fields = entity.getClass().getDeclaredFields();
                for (var field : fields) {
                    if (field.getName().equals("id")) {
                        continue;
                    }
                    field.setAccessible(true);
                    logger.info("Type " + field.getName());
                    if (field.getType() == boolean.class)  {
                        var value = in.nextBoolean();
                        field.set(entity, value);
                    }
                    else if (field.getType() == int.class) {
                        var value = in.nextInt();
                        field.set(entity, value);
                    } 
                    else if (field.getType() == double.class) {
                        var value = in.nextDouble();
                        field.set(entity, value);
                    }
                    else if (field.getType() == UUID.class) {
                        var value = UUID.fromString(in.next());
                        field.set(entity, value);
                    }
                    else if (field.getType() == Date.class) {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                        var strValue = in.next();
                        var value = formatter.parse(strValue);
                        field.set(entity, value);
                    }
                    else {
                        var value = in.next();
                        field.set(entity, value);
                    }
                }
                repository.update(entity);
                break;
            case 3:
                listEntities = repository.getAll();
                for (int i = 0; i < listEntities.size(); i++) {
                    logger.info(String.format("%d. %s%n", i+1, listEntities.get(i).toString()));
                }
                logger.info("\nType id for remove");
                var value = UUID.fromString(in.next());
                repository.remove(value);
                break;
            case 4:
                listEntities = repository.getAll();
                for (int i = 0; i < listEntities.size(); i++) {
                    logger.info(String.format("%d. %s%n", i+1, listEntities.get(i).toString()));
                }
                break;
            default:
                return;
        }
    }    

    public static void main(String[] args) throws Exception {
        Connection connection = connect();
        while (true) {
            logger.info("Choose repo:");
            logger.info("1. User");
            logger.info("2. Role");
            logger.info("3. Author");
            logger.info("4. Book");
            logger.info("5. Genre:");
            logger.info("6. Order:");
            logger.info("7. Review:");
            logger.info("Type any other number to end.");
            int chosenNumber = in.nextInt();
            switch (chosenNumber) {
                case 1:
                    UserRepository userRepo = new UserRepository(connection);
                    User user = new User();
                    drawMenu(userRepo, user);
                    break;
                case 2:
                    RoleRepository roleRepo = new RoleRepository(connection);
                    Role role = new Role();
                    drawMenu(roleRepo, role);
                    break;
                case 3:
                    AuthorRepository authorRepo = new AuthorRepository(connection);
                    Author author = new Author();
                    drawMenu(authorRepo, author);
                    break;
                case 4:
                    BookRepository bookRepo = new BookRepository(connection);
                    Book book = new Book();
                    drawMenu(bookRepo, book);
                    break;
                case 5:
                    GenreRepository genreRepo = new GenreRepository(connection);
                    Genre genre = new Genre();
                    drawMenu(genreRepo, genre);
                    break;
                case 6:
                    OrderRepository orderRepo = new OrderRepository(connection);
                    Order order = new Order();
                    drawMenu(orderRepo, order);
                    break;
                case 7:
                    ReviewRepository reviewRepo = new ReviewRepository(connection);
                    Review review = new Review();
                    drawMenu(reviewRepo, review);
                    break;
                default:
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            logger.warning(e.getMessage());
                        }
                    }
                    return;
            }
        }
        
    }
}

