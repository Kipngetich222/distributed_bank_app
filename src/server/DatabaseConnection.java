// package server;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;

// public class DatabaseConnection {
//     private static final String URL = "jdbc:mysql://localhost:3306/BankDB";
//     private static final String USER = "root"; 
//     private static final String PASSWORD = "PHW#84#vic"; 

//     public static Connection getConnection() throws SQLException {
//         return DriverManager.getConnection(URL, USER, PASSWORD);
//     }
// }



package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/BankDatabase";
    private static final String USER = "root"; 
    private static final String PASSWORD = "PHW#84#vic"; 
    private static Connection connection = null;

    // Static block to initialize the connection
    static {
        try {
            // Load MySQL driver explicitly (optional, but good practice)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish the connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
    }

    // Method to get the existing connection
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }
}
