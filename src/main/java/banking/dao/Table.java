package banking.dao;

import banking.service.Account;
import banking.service.Checksum;

import java.sql.*;
import java.util.Scanner;

public class Table {
    public static String activeAccountNumber;
    public static String activeAccountPIN;
    public static Scanner tableScanner = new Scanner(System.in);
    public static void create() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS card (" +
                "id INTEGER PRIMARY KEY," +
                "number TEXT," +
                "pin TEXT," +
                "balance INTEGER DEFAULT 0" +
                ");";

        // Create a new table by executing the SQL statement
        try (Connection connection = DBConnection.start();
             Statement statement = connection.createStatement()) {
            // Create a new table
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** This method implements adding a new record to the "card" table in a database. The record consists of the
     * number and pin values */
    public static void add(String sql, String number, String pin) {
        // Creates a database connection using the DBConnection.start() method and
        // prepares a SQL statement for execution
        try (Connection connection = DBConnection.start();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, number);
            preparedStatement.setString(2, pin);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update() {
        // Read the income value from user input
        int income = tableScanner.nextInt();

        // SQL statement for updating a table
        String sql = "UPDATE card " +
                "SET balance = balance + ? " +
                "WHERE number = ? AND pin = ?";

        // Update the balance of the active account by executing the SQL statement
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

    public static void transfer() {
        // Read the recipient's card number from user input
        String transferNumber = tableScanner.next();

        // Check the validity of the recipient's card number
        if (!Checksum.isValid(transferNumber)) {
            System.out.println("\nProbably you made a mistake in the card number. Please try again!");
            return;
        }

        // Check if the recipient and sender card numbers are different
        if (transferNumber.equals(activeAccountNumber)) {
            System.out.println("\nYou can't transfer money to the same account!");
            return;
        }

        // Check if the recipient's card number exists
        if (!Account.checkNumber(transferNumber)) {
            System.out.println("Such a card does not exist.");
            return;
        }

        System.out.println("Enter how much money you want to transfer:");
        // Read the transfer amount from user input
        int income = tableScanner.nextInt();

        // Check if the active account has enough balance for the transfer
        if (Account.getBalance(activeAccountNumber, activeAccountPIN) < income) {
            System.out.println("Not enough money!");
            return;
        }

        // SQL statement for updating the table
        String sql = "UPDATE card " +
                "SET balance = CASE " +
                "WHEN number = ? THEN balance + ? " +
                "WHEN number = ? THEN balance - ? " +
                "ELSE balance " +
                "END";

        // Update the balances of the sender and recipient accounts by executing the SQL statement
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

    /** This method implements checking if there is an account with the provided account number (queryNumber) and
     PIN (queryPIN) in the database */
    public static boolean check(String sql, String queryNumber, String queryPIN) {
        try (Connection connection = DBConnection.start();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String number = resultSet.getString("number");
                String PIN = resultSet.getString("pin");

                // Checks if the provided account number and PIN match the values in the current row
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

    /** This method implements checking if there is an account with the provided account number (queryNumber)
     * in the database */
    public static boolean checkNumber(String sql, String queryNumber) {
        try (Connection connection = DBConnection.start();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String number = resultSet.getString("number");

                // Checks if the provided account number matches the value in the current row
                if (queryNumber.equals(number)) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** This method implements retrieving the balance of an account with the provided account number (queryNumber) and
     PIN (queryPIN) */
    public static double getBalance(String sql, String queryNumber, String queryPIN) {
        try (Connection connection = DBConnection.start();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String number = resultSet.getString("number");
                String pin = resultSet.getString("pin");
                int balance = resultSet.getInt("balance");

                // checks if the provided account number and PIN match the values in the current row
                if (queryNumber.equals(number) && queryPIN.equals(pin)) {
                    return balance;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static void delete() {
        // SQL statement for deleting from the table
        String sql = "DELETE FROM card " +
                "WHERE number = ?";

        // Delete the active account from the table by executing the SQL statement
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
