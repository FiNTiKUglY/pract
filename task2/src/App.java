import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;
import java.util.ArrayList;

public class App {
    public static Connection connect() throws SQLException {

        try {
            // Get database credentials from DatabaseConfig class
            var jdbcUrl = "jdbc:postgresql://localhost:5432/library";
            var user = "postgres";
            var password = "29kurlwg";

            // Open a connection
            return DriverManager.getConnection(jdbcUrl, user, password);

        } catch (SQLException  e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Connection connection = connect();
        Db database = new Db(connection);
        ArrayList<User> arr = database.getUsers();
        for (var user : arr) {
            System.out.println(user.id);
        }
    }
}

