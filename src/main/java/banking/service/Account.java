package banking.service;

import banking.dao.Database;
import banking.dao.Table;
import banking.view.Menu;

/**
 * The Account class represents a user account in the banking system.
 * It provides methods for adding an account, checking account validity, retrieving account balance, and managing
 * the login and exit status.
 */
public class Account {

    /**
     * Connects to the database and shows the starting menu.
     */
    public static void connect() {
        Database.connect();
        Menu.showStarting();
    }

    /**
     * Adds a new account to the system with the specified card number and PIN.
     *
     * @param number The card number of the account.
     * @param pin The PIN of the account.
     */
    public void add(String number, String pin) {
        String sql = "INSERT INTO card (number,pin)" +
                "VALUES (?,?)";

        Table.add(sql, number, pin);
    }

    /**
     * Updates the account information in the system.
     */
    public static void update() {
        Table.update();
    }

    /**
     * Transfers funds between accounts.
     */
    public static void transfer() {
        Table.transfer();
    }

    /**
     * Deletes an account from the system.
     */
    public static void delete() {
        Table.delete();
        Menu.showStarting();
    }

    /**
     * Checks if the provided card number and PIN are valid and exist in the system.
     *
     * @param queryNumber The card number to check.
     * @param queryPIN The PIN to check.
     * @return True if the card number and PIN are valid and exist in the system, false otherwise.
     */
    public static boolean check(String queryNumber, String queryPIN) {
        String sql = "SELECT * FROM card";

        return (Table.check(sql, queryNumber, queryPIN));
    }

    /**
     * Checks if the provided card number exists in the system.
     *
     * @param queryNumber The card number to check.
     * @return True if the card number exists in the system, false otherwise.
     */
    public static boolean checkNumber(String queryNumber) {
        String sql = "SELECT * FROM card";

        return Table.checkNumber(sql, queryNumber);
    }

    /**
     * Retrieves the account balance associated with the provided card number and PIN.
     *
     * @param queryNumber The card number of the account.
     * @param queryPIN The PIN of the account.
     * @return The account balance.
     */
    public static double getBalance(String queryNumber, String queryPIN) {
        String sql = "SELECT *  FROM card";

        return Table.getBalance(sql, queryNumber, queryPIN);
    }

    /**
     * Updates the logged-in status of the user.
     *
     * @param loggedIn True if the user is logged in, false otherwise.
     */
    public static void isLoggedIn(boolean loggedIn) {
        if (loggedIn) {
            System.out.println("\nYou have successfully logged in!");
        } else {
            System.out.println("\nYou have successfully logged out!");
            Menu.showStarting();
        }
    }

    /**
     * Updates the exited status of the program.
     *
     * @param exited True if the program is exited, false otherwise.
     */
    public static void isExited(boolean exited) {
        if (exited) {
            System.out.println("\nBye!");
        }
    }
}
