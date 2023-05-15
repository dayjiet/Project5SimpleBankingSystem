import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;
public class DBConnection {
    static Connection connection = null;
    public static Connection start() {

        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(SimpleBankingSystem.url);

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
