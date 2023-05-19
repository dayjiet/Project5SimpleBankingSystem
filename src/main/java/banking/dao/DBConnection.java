package banking.dao;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static banking.SimpleBankingSystem.URL;

/**
 * The DBConnection class is responsible for establishing a connection to the database.
 * It provides a method to start the connection and retrieve the connection object.
 */
public class DBConnection {
    static Connection connection = null;

    /**
     * Starts the database connection.
     *
     * @return The connection object.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection start() throws SQLException {
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(URL);

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
