package banking;

import banking.data.DBConnection;

import java.sql.*;

public class Account {
    public static String activeAccountNumber;
    public static String activeAccountPIN;

    // This method adds a new record to the "card" table in a database. The record consists of the "number" and "pin" values
    public void add(String number, String pin) {
        String sql = "INSERT INTO card (number,pin)" +
                "VALUES (?,?)";

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

    // This method checks if there is an account with the provided account number (queryNumber) and
    // PIN (queryPIN) in the database
    public static boolean check(String queryNumber, String queryPIN) {
        String sql = "SELECT * FROM card";

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

    // This method checks if there is an account with the provided account number (queryNumber) in the database
    public static boolean checkNumber(String queryNumber) {
        String sql = "SELECT * FROM card";

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

    // This method retrieves the balance of an account with the provided account number (queryNumber) and
    // PIN (queryPIN)
    public static double getBalance(String queryNumber, String queryPIN) {
        String sql = "SELECT *  FROM card";

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

    // This method displays a message indicating whether the user has successfully logged in or logged out
    public static void isLoggedIn(boolean loggedIn) {

        if (loggedIn) {
            System.out.println("\nYou have successfully logged in!");
        } else {
            System.out.println("\nYou have successfully logged out!");
        }
    }

    // This method displays a message indicating whether the user has exited the program
    public static void isExited(boolean exited) {

        if (exited) {
            System.out.println("\nBye!");
        }
    }

    // This method retrieves all the accounts from the database and prints their information
    public static void print() {
        String sql = "SELECT *  FROM card";

        try (Connection connection = DBConnection.start();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                long number = resultSet.getLong("number");
                String pin = resultSet.getString("pin");
                int balance  =resultSet.getInt("balance");

                System.out.println("#" + id
                        + " : " + number
                        + " - " + pin
                        + " - " + balance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
