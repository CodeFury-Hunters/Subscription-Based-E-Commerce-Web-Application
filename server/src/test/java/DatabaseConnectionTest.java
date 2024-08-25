import com.hsbc.ecommerce.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Attempt to get a connection to the database
            connection = DatabaseConfig.getConnection();

            // Check if the connection is successful
            if (connection != null) {
                System.out.println("Connected to the database successfully!");
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            // Print the SQL exception message
            e.printStackTrace();
        } finally {
            // Close the connection
            DatabaseConfig.closeConnection(connection);
        }
    }
}