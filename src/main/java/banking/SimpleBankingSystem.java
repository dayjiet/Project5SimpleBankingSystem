package banking;

import banking.data.Database;

public class SimpleBankingSystem {

    public static String URL; // SQLite database connection URL

    public static void main(String[] args) {
        // The database filename
        String fileName = "";

        // Search for the '-fileName' argument in the command line arguments
        for (int i = 0; i < args.length; i++) {

            // If the '-fileName' argument is found, it assigns the next element as the value for fileName.
            if (args[i].equals("-fileName")) {
                // Get the next element as the value for fileName
                fileName = args[i + 1];
                break;
            }

        }

        // If the '-fileName' argument is not found, or it's value is blank/empty, it assigns the default filename
        if (fileName.isBlank() || fileName.isEmpty()) {
            fileName = "card.s3db";
        }

        // Constructs the database URL by concatenating the filename
        URL = "jdbc:sqlite:" + fileName;

        // Calls the connect() method of the Database class to create a new database or connect to an existing one
        Database.connect();
    }
}