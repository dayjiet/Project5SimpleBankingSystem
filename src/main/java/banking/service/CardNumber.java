package banking.service;

/**
 * The CardNumber class represents a credit card number used in the banking system.
 * It provides a method to generate a valid card number and a method to validate a card number.
 */
public class CardNumber {
    /**
     * Generates a valid credit card number.
     *
     * @return The generated card number.
     */
    public static String generate() {
        String fifteenDigitNumber = BIN.get() + CAN.generate();

        return fifteenDigitNumber + Checksum.find(fifteenDigitNumber);
    }

    /**
     * Validates a credit card number.
     *
     * @param cardNumber The card number to validate.
     * @return True if the card number is valid (16 digits), false otherwise.
     */
    public static boolean isValid(String cardNumber) {
        return cardNumber.length() == 16;
    }
}
