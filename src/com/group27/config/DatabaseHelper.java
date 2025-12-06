package com.group27.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class for managing database connections.
 */
public class DatabaseHelper {

    private static final String URL = "jdbc:mysql://localhost:3306/contact_mgmt_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "pass1234";

    private static Connection connection;

    /**
     * Establishes and returns a connection to the database.
     *
     * @return the database connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException e) {
                System.err.println("\u001B[31mMySql JDBC Driver not found. Check LİB folder.\u001B[0m");
                throw new SQLException(e);
            }
        }
        return connection;
    }

    /**
     * Closes the current database connection if it is open.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}