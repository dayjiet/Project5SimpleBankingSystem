package banking.util;

import java.util.Random;

public class PIN {
    // Initializes a constant variable "PIN_LENGTH" with the value 4, representing the length of the card PIN
    static final int PIN_LENGTH = 4;
    // Defines a public static method named "generate" that generates a random PIN
    public static String generate() {
        // Declares and initializes variables "leftLimit" and "rightLimit" for defining
        // the range of random numbers to be generated
        int leftLimit = 0;
        int rightLimit = 9;

        Random random = new Random();
        // Initializes a StringBuilder named "buffer" with the capacity of PIN_LENGTH
        StringBuilder buffer = new StringBuilder(PIN_LENGTH);

        // Iterates through a loop from 0 to PIN_LENGTH-1 to generate each digit of the PIN
        for (int i = 0; i < PIN_LENGTH; i++) {
            // Generates a random number within the specified range and appends it to the "buffer"
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));

            buffer.append(randomLimitedInt);
        }
        return String.valueOf(buffer);
    }
}
