package banking.util;

import java.util.Random;

// Declares a public class named "CAN"
public class CAN {
    // Initializes a constant variable "CAN_LENGTH" with the value 9,
    // representing the length of the customer account number (CAN)
    static final int CAN_LENGTH = 9;

    // Defines a public static method named "generate" that generates a random customer account number (CAN)
    public static String generate() {
        // Declares and initializes variables "leftLimit" and "rightLimit" for defining
        // the range of random numbers to be generated
        int leftLimit = 0;
        int rightLimit = 9;

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(CAN_LENGTH);

        for (int i = 0; i < CAN_LENGTH; i++) {
            // Generates a random number within the specified range and appends it to the "buffer"
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));

            buffer.append(randomLimitedInt);
        }
        return String.valueOf(buffer);
    }
}
