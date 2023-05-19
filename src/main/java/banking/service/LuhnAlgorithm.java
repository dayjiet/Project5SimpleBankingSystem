package banking.service;

public class LuhnAlgorithm {
    // Defines a public static method named "check" which calculates the control number
    // using the Luhn algorithm for a given fifteen-digit number
    public static int check(String fifteenDigitNumber) {
        // Initializes an array named "arrayOfDigits" with the length equal
        // to the length of the input fifteen-digit number
        int[] arrayOfDigits = new int[fifteenDigitNumber.length()];

        // Iterates through a loop to process each digit of the fifteen-digit number
        for (int i = 0; i < fifteenDigitNumber.length(); i++) {
            // Retrieves the character at the current index and converts it to an integer digit
            char digitChar = fifteenDigitNumber.charAt(i);
            int digit = Character.getNumericValue(digitChar);

            // Checks if the index is even or odd to determine the calculation rule for the digit
            if (i % 2 == 0) {
                // If the index is even, multiplies the digit by 2 and subtracts 9 if the result is greater than 9,
                // then stores it in the "arrayOfDigits"
                arrayOfDigits[i] = (2 * digit) > 9 ? (2 * digit) - 9 : (2 * digit);
            } else {
                // If the index is odd, stores the digit directly in the "arrayOfDigits"
                arrayOfDigits[i] = digit;
            }
        }

        int controlNumber = 0;

        // Iterates through the "arrayOfDigits" and adds each digit to the "controlNumber"
        for (int arrayOfDigit : arrayOfDigits) {
            controlNumber += arrayOfDigit;
        }

        return controlNumber;
    }
}
