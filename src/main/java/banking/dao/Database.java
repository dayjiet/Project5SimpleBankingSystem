package banking.dao;

import banking.view.Menu;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The Database class is responsible for connecting to the database and starting the banking system.
 * It provides a method to establish a database connection and initiate the system.
 */
public class Database {

    /**
     * Establishes a connection to the database and starts the banking system.
     */
    public static void connect() {
        try (Connection connection = DBConnection.start()) {

            if (connection != null) {
                Table.create();

                Menu.showStarting();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
