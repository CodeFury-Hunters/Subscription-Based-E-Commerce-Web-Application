package com.hsbc.ecommerce.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce_db";
    private static final String USER = "hunters";
    private static final String PASSWORD = "Hunters@123";

    /**
     * Get a connection to the database.
     *
     * @return Connection object
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Return the connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Close the database connection.
     *
     * @param connection the connection to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
