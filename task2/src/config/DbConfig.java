package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class DbConfig {
    private static final Properties properties = new Properties();
    private static final Logger logger = Logger.getLogger(DbConfig.class.getName());

    public DbConfig() {
        
    }

    static {
        try (InputStream input = DbConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                logger.info("Sorry, unable to find db.properties");
                System.exit(1);
            }

            properties.load(input);
        } catch (IOException e) {
            logger.warning(e.toString());
        }
    }

    public static String getDbUrl() {

        return properties.getProperty("db.url");
    }

    public static String getDbUsername() {
        return properties.getProperty("db.username");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }
}
