// package common;

// import java.rmi.Remote;
// import java.rmi.RemoteException;

// public interface Account extends Remote {
//     double getBalance() throws RemoteException;
//     boolean withdraw(double amount) throws RemoteException; // throws RemoteException
//     void deposit(double amount) throws RemoteException;
//     String getAccountNumber() throws RemoteException;
// }



package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Account extends Remote {
    double getBalance() throws RemoteException;
    boolean withdraw(double amount) throws RemoteException;
    void deposit(double amount) throws RemoteException;
    String getAccountNumber() throws RemoteException;

    // New method to get transaction history
    List<String> getTransactionHistory() throws RemoteException;
}
