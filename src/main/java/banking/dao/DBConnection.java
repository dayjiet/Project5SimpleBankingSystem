package banking.dao;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static banking.SimpleBankingSystem.URL;

public class DBConnection {
    // Declare a static variable to hold the connection object
    static Connection connection = null;
    // Define a method to start the database connection
    public static Connection start() throws SQLException {
        // Create a new SQLite data source
        SQLiteDataSource dataSource = new SQLiteDataSource();
        // Set the URL for the data source
        dataSource.setUrl(URL);

        try {
            // Attempt to establish a connection using the data source
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Return the connection object
        return connection;
    }
}
