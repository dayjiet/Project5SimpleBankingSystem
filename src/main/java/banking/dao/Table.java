package banking.dao;

import banking.service.Account;
import banking.service.Checksum;

import java.sql.*;
import java.util.Scanner;

/**
 * The Table class provides methods to interact with the database table "card".
 * It includes methods to create the table, add records, update balance, perform transfers, check account information,
 * and delete accounts.
 */
public class Table {
    /**
     * The active account number for the current session.
     */
    public static String activeAccountNumber;
    /**
     * The active account PIN for the current session.
     */
    public static String activeAccountPIN;
    /**
     * Scanner object to read user input from the console.
     */
    public static Scanner tableScanner = new Scanner(System.in);

    /**
     * Creates the "card" table in the database if it does not exist.
     */
    public static void create() {
        String sql = "CREATE TABLE IF NOT EXISTS card (" +
                "id INTEGER PRIMARY KEY," +
                "number TEXT," +
                "pin TEXT," +
                "balance INTEGER DEFAULT 0" +
                ");";

        try (Connection connection = DBConnection.start();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new record to the "card" table with the specified card number and PIN.
     *
     * @param sql The SQL query to add a new record.
     * @param number The card number to be added.
     * @param pin The PIN associated with the card number.
     */
    public static void add(String sql, String number, String pin) {
        try (Connection connection = DBConnection.start();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, number);
            preparedStatement.setString(2, pin);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the balance of the active account by adding the specified income.
     */
    public static void update() {
        int income = tableScanner.nextInt();

        String sql = "UPDATE card " +
                "SET balance = balance + ? " +
                "WHERE number = ? AND pin = ?";

        try (Connection connection = DBConnection.start();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, income);
            preparedStatement.setString(2, activeAccountNumber);
            preparedStatement.setString(3, activeAccountPIN);

            preparedStatement.executeUpdate();

            System.out.println("\nIncome was added!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Performs a transfer of money from the active account to the specified card number.
     */
    public static void transfer() {
        String transferNumber = tableScanner.next();

        if (!Checksum.isValid(transferNumber)) {
            System.out.println("\nProbably you made a mistake in the card number. Please try again!");
            return;
        }

        if (transferNumber.equals(activeAccountNumber)) {
            System.out.println("\nYou can't transfer money to the same account!");
            return;
        }

        if (!Account.checkNumber(transferNumber)) {
            System.out.println("Such a card does not exist.");
            return;
        }

        System.out.println("Enter how much money you want to transfer:");
        int income = tableScanner.nextInt();

        if (Account.getBalance(activeAccountNumber, activeAccountPIN) < income) {
            System.out.println("Not enough money!");
            return;
        }

        String sql = "UPDATE card " +
                "SET balance = CASE " +
                "WHEN number = ? THEN balance + ? " +
                "WHEN number = ? THEN balance - ? " +
                "ELSE balance " +
                "END";

        try (Connection connection = DBConnection.start();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, transferNumber);
            preparedStatement.setInt(2, income);
            preparedStatement.setString(3, activeAccountNumber);
            preparedStatement.setInt(4, income);

            preparedStatement.executeUpdate();
            System.out.println("\nSuccess!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if the specified card number and PIN combination exists in the "card" table.
     *
     * @param sql The SQL query to check the account.
     * @param queryNumber The card number to be checked.
     * @param queryPIN The PIN associated with the card number.
     * @return {@code true} if the account exists, {@code false} otherwise.
     */
    public static boolean check(String sql, String queryNumber, String queryPIN) {
        try (Connection connection = DBConnection.start();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String number = resultSet.getString("number");
                String PIN = resultSet.getString("pin");

                if (queryNumber.equals(number) && queryPIN.equals(PIN)) {
                    activeAccountNumber = queryNumber;
                    activeAccountPIN = queryPIN;

                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks if the specified card number exists in the "card" table.
     *
     * @param sql The SQL query to check the card number.
     * @param queryNumber The card number to be checked.
     * @return {@code true} if the card number exists, {@code false} otherwise.
     */
    public static boolean checkNumber(String sql, String queryNumber) {
        try (Connection connection = DBConnection.start();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String number = resultSet.getString("number");

                if (queryNumber.equals(number)) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves the balance of the specified account from the "card" table.
     *
     * @param sql The SQL query to retrieve the balance.
     * @param queryNumber The card number of the account.
     * @param queryPIN The PIN associated with the card number.
     * @return The balance of the account, or -1 if the account is not found.
     */
    public static double getBalance(String sql, String queryNumber, String queryPIN) {
        try (Connection connection = DBConnection.start();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String number = resultSet.getString("number");
                String pin = resultSet.getString("pin");
                int balance = resultSet.getInt("balance");

                if (queryNumber.equals(number) && queryPIN.equals(pin)) {
                    return balance;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    /**
     * Deletes the active account from the "card" table.
     */
    public static void delete() {
        String sql = "DELETE FROM card " +
                "WHERE number = ?";

        try (Connection connection = DBConnection.start();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, activeAccountNumber);

            preparedStatement.executeUpdate();

            System.out.println("\nThe account has been closed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
