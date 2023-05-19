package banking.service;

/**
 * The LuhnAlgorithm class implements the Luhn algorithm used to calculate the control number.
 * It provides a method to check the control number for a given fifteen-digit number.
 */
public class LuhnAlgorithm {

    /**
     * Checks the control number for a given fifteen-digit number using the Luhn algorithm.
     *
     * @param fifteenDigitNumber The fifteen-digit number to calculate the control number for.
     * @return The calculated control number.
     */
    public static int check(String fifteenDigitNumber) {
        int[] arrayOfDigits = new int[fifteenDigitNumber.length()];

        for (int i = 0; i < fifteenDigitNumber.length(); i++) {
            char digitChar = fifteenDigitNumber.charAt(i);
            int digit = Character.getNumericValue(digitChar);

            if (i % 2 == 0) {
                arrayOfDigits[i] = (2 * digit) > 9 ? (2 * digit) - 9 : (2 * digit);
            } else {
                arrayOfDigits[i] = digit;
            }
        }

        int controlNumber = 0;

        for (int arrayOfDigit : arrayOfDigits) {
            controlNumber += arrayOfDigit;
        }

        return controlNumber;
    }
}
