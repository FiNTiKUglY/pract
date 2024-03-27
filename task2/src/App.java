import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;
import java.util.ArrayList;

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
        Db database = new Db(connection);

    }
}

