package banking.service;

public class Checksum {
    // Defines a public static method named "find" which calculates the checksum digit for a given fifteen-digit number
    public static String find(String fifteenDigitNumber) {
        // Calls the "check" method from the "LuhnAlgorithm" class to calculate the control number
        int controlNumber = LuhnAlgorithm.check(fifteenDigitNumber);

        // Returns the calculated checksum digit as a String
        return String.valueOf(controlNumber % 10 == 0 ? 0 : 10 - (controlNumber % 10));
    }

    // Defines a public static method named "isValid" which checks the validity of a given number with checksum
    public static boolean isValid(String number) {
        // Extracts the fifteen-digit number from the input by taking the substring from index 0 to 14
        String fifteenDigitNumber = number.substring(0, 15);
        // Extracts the checksum digit from the input by taking the substring at index 15
        String checksum = number.substring(15);

        // Calls the "check" method from the "LuhnAlgorithm" class
        // to calculate the control number for the fifteen-digit number
        int controlNumber = LuhnAlgorithm.check(fifteenDigitNumber);

        // Checks if the sum of the control number and the parsed checksum digit is divisible by 10
        return (controlNumber + Integer.parseInt(checksum)) % 10 == 0;
    }
}
