package banking;

import banking.service.Account;

/**
 * The SimpleBankingSystem class represents a simple banking system program.
 * It allows users to connect to an SQLite database and perform banking operations.
 */
public class SimpleBankingSystem {

    /**
     * SQLite database connection URL.
     */
    public static String URL;

    /**
     * The main method of the SimpleBankingSystem program.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String fileName = "";

        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-fileName")) {
                // Get the next element as the value for fileName
                fileName = args[i + 1];
                break;
            }

        }

        if (fileName.isBlank() || fileName.isEmpty()) {
            fileName = "card.s3db";
        }

        URL = "jdbc:sqlite:" + fileName;

        Account.connect();
    }
}