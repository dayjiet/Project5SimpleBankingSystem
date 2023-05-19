package banking.service;

import java.util.Random;

/**
 * The CAN class represents a Card Account Number (CAN) used in the banking system.
 * It provides a method to generate a random CAN.
 */
public class CAN {
    static final int CAN_LENGTH = 9;

    /**
     * Generates a random Card Account Number (CAN).
     *
     * @return The generated CAN.
     */
    public static String generate() {
        int leftLimit = 0;
        int rightLimit = 9;

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(CAN_LENGTH);

        for (int i = 0; i < CAN_LENGTH; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));

            buffer.append(randomLimitedInt);
        }
        return String.valueOf(buffer);
    }
}
