import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        System.out.println("\n1. Create an account");
        System.out.println("2. Log into account");
        System.out.println("0. Exit");

        Scanner scanner = new Scanner(System.in);

        char action = scanner.nextLine()
                .trim()
                .charAt(0);

        switch (action) {
            case '1' -> createAccountMenu();
            case '2' -> logInMenu();
            case '0' -> {
                System.out.println("\nBye!");
                scanner.close();
            }
            default -> menu();
        }
    }

    private static void createAccountMenu() {
        System.out.println("\nYour card has been created");
        System.out.println("Your card number:");

        // Bank Identification Number (BIN) must be 400_000
        int BIN = 400_000;
        // Customer account number (CAN)
        int CAN_Length = 10;

        String cardNumber = String.valueOf(BIN) + generateRandomInt(CAN_Length);

        System.out.println(cardNumber);
        System.out.println("Your card PIN:");

        // Card PIN Length
        int PIN_Length = 4;

        String card_PIN = String.valueOf(generateRandomInt(PIN_Length));

        System.out.println(card_PIN);

        AccountInfo.accounts.add(new AccountInfo(cardNumber, card_PIN, 0.00));

        menu();
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

    private static void logInMenu() {
        System.out.println("\nEnter your card number:");

        Scanner scanner = new Scanner(System.in);

        String cardNumber = scanner.nextLine()
                .trim();

        System.out.println("Enter your PIN:");

        String card_PIN = scanner.nextLine()
                .trim();

        if (AccountInfo.checkAccount(cardNumber, card_PIN)) {
            System.out.println("\nYou have successfully logged in!");

            balanceMenu(cardNumber, card_PIN);
        } else {
            System.out.println("\nWrong card number or PIN!");
            menu();
        }

        scanner.close();
    }

    private static void balanceMenu(String cardNumber, String card_PIN) {
        System.out.println("\n1. Balance");
        System.out.println("2. Log out");
        System.out.println("0. Exit");

        Scanner scanner = new Scanner(System.in);

        char action = scanner.nextLine()
                .trim()
                .charAt(0);

        switch (action) {
            case '1' -> {
                System.out.println("\nBalance: " + AccountInfo.BalanceInfo(cardNumber, card_PIN));
                balanceMenu(cardNumber, card_PIN);
            }
            case '2' -> {
                System.out.println("\nYou have successfully logged out!");
                menu();
            }
            case '0' -> System.out.println("\nBye!");
            default -> balanceMenu(cardNumber, card_PIN);
        }
    }
}
