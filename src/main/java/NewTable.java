import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
public class NewTable {
    public static void create() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS card (" +
                "id INTEGER PRIMARY KEY," +
                "number TEXT," +
                "pin TEXT," +
                "balance INTEGER DEFAULT 0" +
                ");";

        // Statement creation
        try (Connection connection = DBConnection.start();
             Statement statement = connection.createStatement()) {
            // Create a new table
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
