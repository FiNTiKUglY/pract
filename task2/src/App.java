import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import config.DbConfig;
import repositories.*;
import entities.*;

public class App {
    private static final Scanner in = new Scanner(System.in);
    private static final Connection connection;

    static {
        Connection tmp = null;
        try {
            tmp = connect();
        } catch (SQLException e) {
            System.console().printf(e.getMessage());
        }
        connection = tmp;
    }

    public static Connection connect() throws SQLException {
        
        try {
            var jdbcUrl = DbConfig.getDbUrl();
            var user = DbConfig.getDbUsername();
            var password = DbConfig.getDbPassword();

            return DriverManager.getConnection(jdbcUrl, user, password);

        } catch (SQLException  e) {
            System.console().printf(e.getMessage());
            return null;
        }
    }

    public static void printEntities(BaseRepository repository) throws SQLException {
        var listEntities = repository.getAll();
        for (int i = 0; i < listEntities.size(); i++) {
            System.console().printf(String.format("%d. %s%n%n", i+1, listEntities.get(i).toString()));
        }
    }

    public static void printEntities(String fieldName) throws SQLException{
        if (fieldName.startsWith("book")) {
            BookRepository bookRepository = new BookRepository(connection);
            printEntities(bookRepository);
        }
        else if (fieldName.startsWith("author")) {
            AuthorRepository authorRepository = new AuthorRepository(connection);
            printEntities(authorRepository);
        }
        else if (fieldName.startsWith("role")) {
            RoleRepository roleRepository = new RoleRepository(connection);
            printEntities(roleRepository);
        }
        else if (fieldName.startsWith("order")) {
            OrderRepository orderRepository = new OrderRepository(connection);
            printEntities(orderRepository);
        }
        else if (fieldName.startsWith("genre")) {
            GenreRepository genreRepository = new GenreRepository(connection);
            printEntities(genreRepository);
        }
        else if (fieldName.startsWith("user")) {
            UserRepository userRepository = new UserRepository(connection);
            printEntities(userRepository);
        }
    }

    public static void add(BaseRepository repository, IEntity entity) throws IllegalAccessException, ParseException, SQLException {
        var fields = entity.getClass().getDeclaredFields();
        for (var field : fields) {
            field.setAccessible(true);
            System.console().printf("Type " + field.getName());
            if (field.getType() == boolean.class)  {
                System.console().printf(" (boolean)\n");
                String valueStr = in.next();
                var value = Boolean.parseBoolean(valueStr);
                field.set(entity, value);
            }
            else if (field.getType() == int.class) {
                System.console().printf(" (int number)\n");
                String valueStr = in.next();
                var value = Integer.parseInt(valueStr);
                field.set(entity, value);
            } 
            else if (field.getType() == double.class) {
                System.console().printf(" (real number)\n");
                String valueStr = in.next();
                var value = Double.parseDouble(valueStr);
                field.set(entity, value);
            }
            else if (field.getType() == UUID.class && field.getName().contains("Id")) {
                System.console().printf(" (uuid with xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx format)\n");
                printEntities(field.getName());
                var value = UUID.fromString(in.next());
                field.set(entity, value);
            }
            else if (field.getType() == UUID.class && field.getName().equals("id")) {
                System.console().printf(" (uuid with xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx format)\n");
                var value = UUID.fromString(in.next());
                field.set(entity, value);
            }
            else if (field.getType() == Date.class) {
                System.console().printf(" (date with dd-MM-yyyy format)\n");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                var strValue = in.next();
                var value = formatter.parse(strValue);
                field.set(entity, value);
            }
            else if (field.getType() == String.class) {
                System.console().printf(" (string)\n");
                var value = in.next();
                field.set(entity, value);
            }
            else if (field.getName().equals("booksIds")) {
                List<UUID> booksIds = (List<UUID>)field.get(entity);
                System.console().printf(" (uuid with xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx format)\n");
                printEntities(field.getName());
                while (true) {
                    System.console().printf("Type 'cancel' if you want to stop add books\n");
                    var value = in.next();
                    if (value.equals("cancel")) {
                        break;
                    }
                    var valueUUID = UUID.fromString(value);
                    booksIds.add(valueUUID);
                }
                field.set(entity, booksIds);
            }
            else if (field.getName().equals("genresIds")) {
                List<UUID> genreIds = (List<UUID>)field.get(entity);
                System.console().printf(" (uuid with xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx format)\n");
                printEntities(field.getName());
                while (true) {
                    System.console().printf("Type 'cancel' if you want to stop add genres\n");
                    var value = in.next();
                    if (value.equals("cancel")) {
                        break;
                    }
                    var valueUUID = UUID.fromString(value);
                    genreIds.add(valueUUID);
                }
                field.set(entity, genreIds);
            }
            else {
                System.console().printf("\nUnknown type\n");
            }
        }
        repository.add(entity);
    }

    public static void update(BaseRepository repository, IEntity entity) throws IllegalAccessException, ParseException, SQLException {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (var field : fields) {
            if (field.getName().equals("id")) {
                continue;
            }
            field.setAccessible(true);
            System.console().printf("Type " + field.getName());
            if (field.getType() == boolean.class)  {
                System.console().printf(" (boolean)\n");
                String valueStr = in.next();
                var value = Boolean.parseBoolean(valueStr);
                field.set(entity, value);
            }
            else if (field.getType() == int.class) {
                System.console().printf(" (int number)\n");
                String valueStr = in.next();
                var value = Integer.parseInt(valueStr);
                field.set(entity, value);
            } 
            else if (field.getType() == double.class) {
                System.console().printf(" (real number)\n");
                String valueStr = in.next();
                var value = Double.parseDouble(valueStr);
                field.set(entity, value);
            }
            else if (field.getType() == UUID.class && field.getName().contains("Id")) {
                System.console().printf(" (uuid with xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx format)\n");
                printEntities(field.getName());
                var value = UUID.fromString(in.next());
                field.set(entity, value);
            }
            else if (field.getType() == Date.class) {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                System.console().printf(" (date with dd-MM-yyyy format)\n");
                var strValue = in.next();
                var value = formatter.parse(strValue);
                field.set(entity, value);
            }
            else if (field.getType() == String.class) {
                System.console().printf(" (string)\n");
                var value = in.next();
                field.set(entity, value);
            }
            else if (field.getName().equals("booksIds")) {
                List<UUID> booksIds = (List<UUID>)field.get(entity);
                System.console().printf(" (uuid with xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx format)\n");
                printEntities(field.getName());
                while (true) {
                    System.console().printf("Type 'cancel' if you want to stop add books\n");
                    var value = in.next();
                    if (value.equals("cancel")) {
                        break;
                    }
                    var valueUUID = UUID.fromString(value);
                    booksIds.add(valueUUID);
                }
                field.set(entity, booksIds);
            }
            else if (field.getName().equals("genresIds")) {
                List<UUID> genreIds = (List<UUID>)field.get(entity);
                System.console().printf(" (uuid with xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx format)\n");
                printEntities(field.getName());
                while (true) {
                    System.console().printf("Type 'cancel' if you want to stop add genres\n");
                    var value = in.next();
                    if (value.equals("cancel")) {
                        break;
                    }
                    var valueUUID = UUID.fromString(value);
                    genreIds.add(valueUUID);
                }
                field.set(entity, genreIds);
            }
            else {
                System.console().printf("\nUnknown type\n");
            }
        }
        repository.update(entity);
    }

    public static void drawMenu(BaseRepository repository, IEntity entity) throws Exception {
        try {
            System.console().printf("Choose action:\n");
            System.console().printf("1. Add\n");
            System.console().printf("2. Update\n");
            System.console().printf("3. Remove\n");
            System.console().printf("4. Show\n");
            String chosenNumberStr = in.next();
            var chosenNumber = Integer.parseInt(chosenNumberStr);
            switch (chosenNumber) {
                case 1:
                    add(repository, entity);
                    break;
                case 2:
                    printEntities(repository);
                    System.console().printf("\nType id for update\n");
                    var id = UUID.fromString(in.next());
                    entity = repository.getById(id);
                    update(repository, entity);
                    break;
                case 3:
                    printEntities(repository);
                    System.console().printf("\nType id for remove\n");
                    var value = UUID.fromString(in.next());
                    repository.remove(value);
                    break;
                case 4:
                    printEntities(repository);
                    break;
                default:
                    return;
            }    
        } catch (NumberFormatException e) {
            System.console().printf("Typed value not number, try again\n");
        } catch (IllegalArgumentException e) {
            System.console().printf("Not correct format\n");
        } catch (ParseException e) {
            System.console().printf("Not correct date format\n");
        } catch (SQLException e) {
            System.console().printf("UUID not found in db or connection lost\n");
        }
    }    

    public static void main(String[] args) throws Exception {
        if (connection == null) {
            System.console().printf("Connection not found, cancelled...\n");
            return;
        }
        while (true) {
            try {
                System.console().printf("Choose repo:\n");
                System.console().printf("1. User\n");
                System.console().printf("2. Role\n");
                System.console().printf("3. Author\n");
                System.console().printf("4. Book\n");
                System.console().printf("5. Genre:\n");
                System.console().printf("6. Order:\n");
                System.console().printf("7. Review:\n");
                System.console().printf("Type any other number to end.\n");
                String chosenNumberStr = in.next();
                var chosenNumber = Integer.parseInt(chosenNumberStr);
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
                        connection.close();
                        return;
                }
            
            } catch (NumberFormatException e) {
                System.console().printf("Typed value not int, try again\n");
            } catch (SQLException e) {
                System.console().printf(e.getMessage());
            }
        }
        
    }
}

