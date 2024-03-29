import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;
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
                    else {
                        var value = in.next();
                        field.set(entity, value);
                    }
                }
                repository.add(entity);
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
                    AuthorRepository userRepo = new AuthorRepository(connection);
                    Author user = new Author();
                    drawMenu(userRepo, user);
                    break;
                default:
                    return;
            }
            connection.close();
        }
    }
}

