// package server;

// import common.Account;
// import java.rmi.Naming;
// import java.rmi.registry.LocateRegistry;
// import java.rmi.registry.Registry;
// import java.net.InetAddress;

// public class BankServer {
//     public static void main(String[] args) {
//         try {
//             // Get server IP dynamically
//             String serverIP = InetAddress.getLocalHost().getHostAddress(); 
//             int port = 3000; // RMI registry port

//             // Check if RMI registry is already running, otherwise create it
//             try {
//                 Registry registry = LocateRegistry.getRegistry(port);
//                 registry.list(); // Check if it responds
//                 System.out.println("RMI Registry already running on port " + port);
//             } catch (Exception e) {
//                 LocateRegistry.createRegistry(port);
//                 System.out.println("RMI Registry started on port " + port);
//             }

//             // Create an Account object and bind it to the RMI registry
//             Account account = new AccountImpl("123456789");
//             String bindLocation = "rmi://" + serverIP + ":" + port + "/BankAccount";
//             Naming.rebind(bindLocation, account);

//             System.out.println("Server started at: " + bindLocation);
//         } catch (Exception e) {
//             e.printStackTrace();
//             System.out.println("Server failed to start!");
//         }
//     }
// }PHW#84#vic



package server;

import common.Account;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BankServer {
    public static void main(String[] args) {
        try {
            // Get server IP dynamically
            String serverIP = InetAddress.getLocalHost().getHostAddress(); 
            int port = 3000; // RMI registry port

            // Start the RMI registry if not already running
            try {
                Registry registry = LocateRegistry.getRegistry(port);
                registry.list(); // Check if it responds
                System.out.println("RMI Registry already running on port " + port);
            } catch (Exception e) {
                LocateRegistry.createRegistry(port);
                System.out.println("RMI Registry started on port " + port);
            }

            // Connect to MySQL database
            Connection connection = connectToDatabase();
            if (connection == null) {
                System.out.println("Failed to connect to the database. Server shutting down.");
                return;
            }

            // Create an Account object and bind it to the RMI registry
            String accountNumber = "123456"; // Retrieve from user input or another source
            Account account = new AccountImpl(connection, accountNumber);
            String bindLocation = "rmi://" + serverIP + ":" + port + "/BankAccount";
            Naming.rebind(bindLocation, account);

            System.out.println("Server started at: " + bindLocation);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Server failed to start!");
        }
    }

    private static Connection connectToDatabase() {
        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Connect to the database
            String url = "jdbc:mysql://localhost:3306/BankDatabase"; // Change DB name if needed
            String user = "root";  // Change username if necessary
            String password = "PHW#84#vic";  // Your DB password
    
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully!");
            
            return connection; // Return the connection object
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database.");
            e.printStackTrace();
        }
        return null; // Return null only if an error occurs
    }
    
}
