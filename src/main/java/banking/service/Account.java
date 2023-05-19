package banking.service;

import banking.dao.*;

public class Account {

    /** This method adds a new record to the card table in a database. The record consists of the number and
    pin values */
    public void add(String number, String pin) {
        String sql = "INSERT INTO card (number,pin)" +
                "VALUES (?,?)";

        Table.add(sql, number, pin);
    }

    /** This method checks if there is an account with the provided account number (queryNumber) and
    PIN (queryPIN) in the database */
    public static boolean check(String queryNumber, String queryPIN) {
        String sql = "SELECT * FROM card";

        return (Table.check(sql, queryNumber, queryPIN));
    }

    /** This method checks if there is an account with the provided account number (queryNumber) in the database */
    public static boolean checkNumber(String queryNumber) {
        String sql = "SELECT * FROM card";

        return Table.checkNumber(sql, queryNumber);
    }

    /** This method retrieves the balance of an account with the provided account number (queryNumber) and
    PIN (queryPIN) */
    public static double getBalance(String queryNumber, String queryPIN) {
        String sql = "SELECT *  FROM card";

        return Table.getBalance(sql, queryNumber, queryPIN);
    }

    /** This method displays a message indicating whether the user has successfully logged in or logged out*/
    public static void isLoggedIn(boolean loggedIn) {
        if (loggedIn) {
            System.out.println("\nYou have successfully logged in!");
        } else {
            System.out.println("\nYou have successfully logged out!");
        }
    }

    /** This method displays a message indicating whether the user has exited the program */
    public static void isExited(boolean exited) {
        if (exited) {
            System.out.println("\nBye!");
        }
    }
}
