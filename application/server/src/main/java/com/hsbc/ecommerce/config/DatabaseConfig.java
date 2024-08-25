package com.hsbc.ecommerce.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/ecommerce_db";
    private static final String USER = "hunters";
    private static final String PASSWORD = "Hunters@123";

    private static DatabaseConfig instance;
    private Connection connection;

    
    private DatabaseConfig() throws SQLException {
        try {
            // Load the JDBC driver if required (depends on the JDBC version)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Initialize the connection
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            throw new SQLException("Failed to create database connection", ex);
        }
    }

    // Step 3: Public static method to provide access to the instance
    public static DatabaseConfig getInstance() throws SQLException {
        if (instance == null) {
            synchronized (DatabaseConfig.class) {
                if (instance == null) {
                    instance = new DatabaseConfig();
                }
            }
        }
        return instance;
    }

    // Method to get the Connection object
    public Connection getConnection() {
        return connection;
    }

    // Optional: Method to close the connection (not usually part of Singleton, but useful)
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}