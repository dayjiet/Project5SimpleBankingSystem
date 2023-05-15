import java.sql.*;

public class AccountInfo {
    public void add(long number, String pin) {
        String sql = "INSERT INTO card (number,pin)" +
                "VALUES (?,?)";

        try (Connection connection = DBConnection.start();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, number);
            preparedStatement.setString(2, pin);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean check(long queryNumber, String queryPin) {
        String sql = "SELECT *  FROM card";

        try (Connection connection = DBConnection.start();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long number = resultSet.getLong("number");
                String pin = resultSet.getString("pin");

                if (queryNumber == number && queryPin.equals(pin)) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static double getBalance(long queryNumber, String queryPin) {

        String sql = "SELECT *  FROM card";

        try (Connection connection = DBConnection.start();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long number = resultSet.getLong("number");
                String pin = resultSet.getString("pin");
                int balance  =resultSet.getInt("balance");

                if (queryNumber == number && queryPin.equals(pin)) {
                    return balance;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static void logIn(boolean loggedIn) {

        if (loggedIn) {
            System.out.println("\nYou have successfully logged in!");
        } else {
            System.out.println("\nYou have successfully logged out!");
        }
    }

    public static void exit(boolean exits) {

        if (exits) {
            System.out.println("\nBye!");
            SimpleBankingSystem.scanner.close();
        }
    }

    public static void printInfo() {
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
