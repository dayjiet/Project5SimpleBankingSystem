package banking.dao;

import banking.view.Menu;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    /** Establishes a connection to the database */
    public static void connect() {
        try (Connection connection = DBConnection.start()) {

            if (connection != null) {
                // If connection is successful, create a new table named 'card'
                Table.create();

                // Displays the starting menu
                Menu.showStarting();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
