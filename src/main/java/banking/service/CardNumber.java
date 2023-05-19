package banking.service;

public class CardNumber {
    public static String generate() {
        // Generate a 15-digit number
        String fifteenDigitNumber = BIN.get() + CAN.generate();

        // Return a 16-digit customer card number by appending a checksum
        return fifteenDigitNumber + Checksum.find(fifteenDigitNumber);
    }

    public static boolean isValid(String cardNumber) {
        return cardNumber.length() == 16;
    }
}
