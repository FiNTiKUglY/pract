import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

import repositories.*;

public class App {
    public static Connection connect() throws SQLException {

        try {
            var jdbcUrl = "jdbc:postgresql://localhost:5432/library";
            var user = "postgres";
            var password = "29kurlwg";

            return DriverManager.getConnection(jdbcUrl, user, password);

        } catch (SQLException  e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Connection connection = connect();
        var roleRepo = new RoleRepository(connection);
        roleRepo.removeRole(UUID.fromString("6fe895ca-abc8-473e-908c-bf673a76c190"));
        var list = roleRepo.getRoles();
        for (var ent : list) {
            System.out.println(ent.toString());
        }
    }
}

