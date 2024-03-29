import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;

import config.DbConfig;
import repositories.*;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());

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

    public static void main(String[] args) throws Exception {
        Connection connection = connect();
        var roleRepo = new RoleRepository(connection);
        roleRepo.removeRole(UUID.fromString("6fe895ca-abc8-473e-908c-bf673a76c190"));
    }
}

