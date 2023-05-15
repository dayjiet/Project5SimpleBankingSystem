import java.sql.Connection;
import java.sql.SQLException;
public class NewDatabase {
    public static void connect() {
        try (Connection connection = DBConnection.start()) {

            if (connection != null) {
                // Create new 'card' table
                NewTable.create();

                SimpleBankingSystem.showStartingMenu();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
