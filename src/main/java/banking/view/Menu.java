package banking.view;

import banking.service.Account;
import banking.service.CardNumber;
import banking.service.PIN;

import java.util.Scanner;

/**
 * The Menu class represents a user interface for a banking system.
 * It provides various options for creating an account, logging in, and accessing account features such as balance
 * inquiry, adding income, transferring funds, closing the account, and logging out.
 */
public class Menu {

    public static Scanner menuScanner = new Scanner(System.in);

    /**
     * Displays the starting menu options:
     * Create an account
     * Log into account
     * Exit
     */
    public static void showStarting() {
        System.out.println("\n1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");

        char action = menuScanner.nextLine()
                .trim()
                .charAt(0);

        switch (action) {
            case '0' -> Account.isExited(true);
            case '1' -> showNewAccount();
            case '2' -> showLogIn();
            default -> showStarting();
        }
    }

    /**
     * Displays the menu options for creating a new account.
     * Generates a new card number and PIN, adds the account to the system, and then shows the starting menu.
     */
    private static void showNewAccount() {
        System.out.println("\nYour card has been created");
        System.out.println("Your card number:");

        String cardNumber = CardNumber.generate();

        System.out.println(cardNumber);
        System.out.println("Your card PIN:");

        String card_PIN = PIN.generate();

        System.out.println(card_PIN);

        Account account = new Account();
        account.add(cardNumber, card_PIN);

        showStarting();
    }

    /**
     * Displays the menu options for logging into an existing account.
     * Asks for the card number and PIN, validates them, and if successful, sets the user as logged in and shows
     * the account menu. Otherwise, displays an error message and shows the starting menu.
     */
    private static void showLogIn() {
        Scanner logInScanner = new Scanner(System.in);

        System.out.println("\nEnter your card number:");
        String cardNumber = logInScanner.next();

        if (!CardNumber.isValid(cardNumber)) {
            System.out.println("\nProbably you made a mistake in the card number. Please try again!");
            showLogIn();
        }

        System.out.println("Enter your PIN:");
        String card_PIN = logInScanner.next();

        if (!PIN.isValid(card_PIN)) {
            System.out.println("\nProbably you made a mistake in the PIN. Please try again!");
            showLogIn();
        }

        if (Account.check(cardNumber, card_PIN)) {
            Account.isLoggedIn(true);
            showAccount(cardNumber, card_PIN);
        } else {
            System.out.println("\nWrong card number or PIN!");
            showStarting();
        }

        logInScanner.close();
    }

    /**
     * Displays the menu options for an authenticated user's account.
     * Provides options for balance inquiry, adding income, transferring funds,
     * closing the account, logging out, and exiting the system.
     * @param cardNumber The card number of the logged-in account.
     * @param card_PIN The PIN of the logged-in account.
     */
    public static void showAccount(String cardNumber, String card_PIN) {
        System.out.println("\n1. Balance");
        System.out.println("2. Add income");
        System.out.println("3. Do transfer");
        System.out.println("4. Close account");
        System.out.println("5. Log out");
        System.out.println("0. Exit");

        char action = menuScanner.nextLine()
                .trim()
                .charAt(0);

        switch (action) {
            case '0' -> Account.isExited(true);
            case '1' -> {
                System.out.println("\nBalance: " + Account.getBalance(cardNumber, card_PIN));
                showAccount(cardNumber, card_PIN);
            }
            case '2' -> {
                System.out.println("\nEnter income:");
                Account.update();
                showAccount(cardNumber, card_PIN);
            }
            case '3' -> {
                System.out.println("\nTransfer");
                System.out.println("Your card number:");
                Account.transfer();
                showAccount(cardNumber, card_PIN);
            }
            case '4' -> Account.delete();
            case '5' -> Account.isLoggedIn(false);
            default -> showAccount(cardNumber, card_PIN);
        }
    }
}
