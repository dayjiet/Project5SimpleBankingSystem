import java.util.Random;
import java.util.Scanner;

public class Main {

    static final int BIN = 400_000; // Bank Identification Number (BIN) must be 400_000
    static final int CAN_LENGTH = 10; // 9 digit customer account number (CAN) and 1 digit check digit (checksum)
    static final int PIN_LENGTH = 4; // Card PIN Length
    static Scanner scanner = new Scanner(System.in);
    static boolean logedIn = false;

    public static void main(String[] args) {
        showStartingMenu();
    }

    private static void showStartingMenu() {
        System.out.println("\n1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");

        char action = scanner.nextLine()
                .trim()
                .charAt(0);

        switch (action) {
            case '0' -> {
                System.out.println("\nBye!");
                scanner.close();
            }
            case '1' -> showNewAccountMenu();
            case '2' -> showLogInMenu();
            default -> showStartingMenu();
        }
    }

    private static void showNewAccountMenu() {
        System.out.println("\nYour card has been created");
        System.out.println("Your card number:");

        // 16 digit customer card number
        String cardNumber = String.valueOf(BIN) + generateRandomInt(CAN_LENGTH);

        System.out.println(cardNumber);
        System.out.println("Your card PIN:");

        String card_PIN = String.valueOf(generateRandomInt(PIN_LENGTH));

        System.out.println(card_PIN);

        AccountInfo.accounts.add(new AccountInfo(cardNumber, card_PIN, 0.00));

        showStartingMenu();
    }

    private static StringBuilder generateRandomInt(int CANLength) {
        int leftLimit = 0;
        int rightLimit = 9;

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(CANLength);

        for (int i = 0; i < CANLength; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));

            buffer.append(randomLimitedInt);
        }

        return buffer;
    }

    private static void showLogInMenu() {
        System.out.println("\nEnter your card number:");

        // 16 digit customer card number
        String cardNumber = scanner.nextLine()
                .trim();

        System.out.println("Enter your PIN:");

        // Card PIN
        String card_PIN = scanner.nextLine()
                .trim();

        if (AccountInfo.checkAccountExistence(cardNumber, card_PIN)) {
            System.out.println("\nYou have successfully logged in!");
            logedIn = true;
            showAccountMenu(cardNumber, card_PIN);
        } else {
            System.out.println("\nWrong card number or PIN!");
            showStartingMenu();
        }
    }

    private static void showAccountMenu(String cardNumber, String card_PIN) {
        System.out.println("\n1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");

        char action = scanner.nextLine()
                .trim()
                .charAt(0);

        switch (action) {
            case '0' ->  {
                System.out.println("\nBye!");
                scanner.close();
            }
            case '1' -> {
                System.out.println("\nBalance: " + AccountInfo.getAccountBalance(cardNumber, card_PIN));
                showAccountMenu(cardNumber, card_PIN);
            }
            case '2' -> {
                System.out.println("\nYou have successfully logged out!");
                logedIn = false;
                showStartingMenu();
            }
            default -> showAccountMenu(cardNumber, card_PIN);
        }
    }
}