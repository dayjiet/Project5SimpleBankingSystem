package banking.service;

/**
 * The Checksum class represents the checksum used to validate a credit card number.
 * It provides a method to find the checksum and a method to validate a checksum.
 */
public class Checksum {

    /**
     * Finds the checksum for a given fifteen-digit number.
     *
     * @param fifteenDigitNumber The fifteen-digit number to calculate the checksum for.
     * @return The calculated checksum.
     */
    public static String find(String fifteenDigitNumber) {
        int controlNumber = LuhnAlgorithm.check(fifteenDigitNumber);

        return String.valueOf(controlNumber % 10 == 0 ? 0 : 10 - (controlNumber % 10));
    }

    /**
     * Validates a checksum for a given number.
     *
     * @param number The number containing the fifteen-digit number and the checksum.
     * @return True if the checksum is valid, false otherwise.
     */
    public static boolean isValid(String number) {
        String fifteenDigitNumber = number.substring(0, 15);
        String checksum = number.substring(15);

        int controlNumber = LuhnAlgorithm.check(fifteenDigitNumber);

        return (controlNumber + Integer.parseInt(checksum)) % 10 == 0;
    }
}
