package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseController {
  
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/assignmentsystem";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "junaid08";

    // Establishes a database connection
    public static Connection connect() {
        try {
            return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }

    // Closes the database connection
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    

}


