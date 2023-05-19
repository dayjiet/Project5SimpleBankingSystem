package banking.view;

import banking.service.*;
import banking.dao.Table;

import java.util.Scanner;

public class Menu {
    public static Scanner menuScanner = new Scanner(System.in);

    /** Displays options to create an account, log into an account, or exit */
    public static void showStarting() {
        System.out.println("\n1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");

        // Read the user's chosen action from the input
        char action = menuScanner.nextLine()
                .trim()
                .charAt(0);

        // Perform the corresponding action based on the user's choice
        switch (action) {
            case '0' -> Account.isExited(true); // Exit the program
            case '1' -> showNewAccount(); // Proceed to create a new account
            case '2' -> showLogIn(); // Proceed to log into an existing account
            default -> showStarting(); // Invalid input, show the starting menu again
        }
    }

    /** Generates a new card number and PIN, adds the new card information to the database, and
    prints the information to the terminal */
    private static void showNewAccount() {
        System.out.println("\nYour card has been created");
        System.out.println("Your card number:");

        // Generate a 16-digit customer card number by appending a checksum
        String cardNumber = CardNumber.generate();

        System.out.println(cardNumber);
        System.out.println("Your card PIN:");

        // Generate a 4-digit PIN
        String card_PIN = PIN.generate();

        System.out.println(card_PIN);

        // Add new card information to the database
        Account account = new Account();
        account.add(cardNumber, card_PIN);

        // Go back to the starting menu
        showStarting();
    }

    /** Prompts the user to enter their card number and PIN, checks if the entered information is valid, and
    either logs the user in or displays an error message */
    private static void showLogIn() {
        Scanner logInScanner = new Scanner(System.in);

        System.out.println("\nEnter your card number:");
        // Read the user's card number
        String cardNumber = logInScanner.next();

        if (!CardNumber.isValid(cardNumber)) {
            System.out.println("\nProbably you made a mistake in the card number. Please try again!");
            showLogIn();
        }

        System.out.println("Enter your PIN:");
        // Read the user's PIN
        String card_PIN = logInScanner.next();

        if (!PIN.isValid(card_PIN)) {
            System.out.println("\nProbably you made a mistake in the PIN. Please try again!");
            showLogIn();
        }

        if (Account.check(cardNumber, card_PIN)) {
            // Log in the user
            Account.isLoggedIn(true);
            // Proceed to the account information menu
            showAccount(cardNumber, card_PIN);
        } else {
            System.out.println("\nWrong card number or PIN!");
            // Display the starting menu again
            showStarting();
        }

        logInScanner.close();
    }

    /** Displays options to view balance, add income, perform a transfer, close the account, log out, or exit */
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

        // Perform the corresponding action based on the user's choice
        switch (action) {
            case '0' -> Account.isExited(true); // Exit the program
            case '1' -> {
                System.out.println("\nBalance: " + Account.getBalance(cardNumber, card_PIN));
                // Go back to the account information menu
                showAccount(cardNumber, card_PIN);
            }
            case '2' -> {
                System.out.println("\nEnter income:");
                // Perform the operation to add income
                Table.update();
                // Go back to the account information menu
                showAccount(cardNumber, card_PIN);
            }
            case '3' -> {
                System.out.println("\nTransfer");
                System.out.println("Your card number:");
                // Perform the operation to transfer funds
                Table.transfer();
                // Go back to the account information menu
                showAccount(cardNumber, card_PIN);
            }
            case '4' -> {
                // Close the account
                Table.delete();
                // Display the starting menu
                showStarting();
            }
            case '5' -> {
                // Log out the user
                Account.isLoggedIn(false);
                // Display the starting menu
                showStarting();
            }
            // Invalid input, go back to the account information menu
            default -> showAccount(cardNumber, card_PIN);
        }
    }
}
