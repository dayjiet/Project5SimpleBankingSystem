import java.util.Random;
import java.util.Scanner;

public class SimpleBankingSystem {

    static String url; // SQLite database Connection URL
    static final int BIN = 400_000; // Bank Identification Number (BIN) must be 400_000
    static final int CAN_LENGTH = 9; // 9 digit customer account number (CAN)
    static final int PIN_LENGTH = 4; // Card PIN Length
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        // Database filename
        String fileName = "temp";

        /**
        String fileName = null;

        // Search for the '-fileName' argument in the command line arguments
        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-fileName")) {
                // Get the next element as the value for fileName
                fileName = args[i + 1];
                break;
            }

        }
         */

        url = "jdbc:sqlite:" + fileName;

        // Create new database
        NewDatabase.connect();
    }

    static void showStartingMenu() {
        System.out.println("\n1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");

        char action = scanner.nextLine()
                .trim()
                .charAt(0);

        switch (action) {
            case '0' -> AccountInfo.exit(true);
            case '1' -> showNewAccountMenu();
            case '2' -> showLogInMenu();
            default -> showStartingMenu();
        }
    }

    private static void showNewAccountMenu() {
        System.out.println("\nYour card has been created");
        System.out.println("Your card number:");

        // 15 digit number
        long fifteenDigitNumber = BIN * 1000_000_000L + generateCAN();

        // 16 digit customer card number
        long cardNumber = fifteenDigitNumber * 10 + findChecksum(fifteenDigitNumber);

        System.out.println(cardNumber);
        System.out.println("Your card PIN:");

        // 4 digit PIN
        String card_PIN = generatePIN();

        System.out.println(card_PIN);

        // Adding new card information to SQLite database
        AccountInfo accountInformation = new AccountInfo();
        accountInformation.add(cardNumber, card_PIN);

        // Printing new card information to the terminal
        AccountInfo.printInfo();
        showStartingMenu();
    }

    private static long generateCAN() {
        int leftLimit = 0;
        int rightLimit = 9;

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(CAN_LENGTH);

        for (int i = 0; i < CAN_LENGTH; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));

            buffer.append(randomLimitedInt);
        }
        return Long.parseLong(String.valueOf(buffer));
    }

    private static String generatePIN() {
        int leftLimit = 0;
        int rightLimit = 9;

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(PIN_LENGTH);

        for (int i = 0; i < PIN_LENGTH; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));

            buffer.append(randomLimitedInt);
        }
        return String.valueOf(buffer);
    }

    private static int findChecksum(long fifteenDigitNumber) {
        int controlNumber = LuhnAlgorithm.findChecksum(fifteenDigitNumber);

        return controlNumber % 10 == 0 ? 0 : 10 - (controlNumber % 10);
    }

    private static void showLogInMenu() {
        System.out.println("\nEnter your card number:");

        // 16 digit customer card number
        long cardNumber = Long.parseLong(scanner.nextLine());

        System.out.println("Enter your PIN:");

        // Card PIN
        String card_PIN = scanner.nextLine()
                .trim();

        if (AccountInfo.check(cardNumber, card_PIN)) {
            AccountInfo.logIn(true);
            showAccountMenu(cardNumber, card_PIN);
        } else {
            System.out.println("\nWrong card number or PIN!");
            showStartingMenu();
        }
    }

    private static void showAccountMenu(long cardNumber, String card_PIN) {
        System.out.println("\n1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");

        char action = scanner.nextLine()
                .trim()
                .charAt(0);

        switch (action) {
            case '0' -> AccountInfo.exit(true);
            case '1' -> {
                System.out.println("\nBalance: " + AccountInfo.getBalance(cardNumber, card_PIN));
                showAccountMenu(cardNumber, card_PIN);
            }
            case '2' -> {
                AccountInfo.logIn(false);
                showStartingMenu();
            }
            default -> showAccountMenu(cardNumber, card_PIN);
        }
    }
}