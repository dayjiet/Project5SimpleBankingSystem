package banking.service;

import java.util.Random;

/**
 * The PIN class represents a Personal Identification Number (PIN) used in the banking system.
 * It provides methods to generate a random PIN and validate a PIN.
 */
public class PIN {
    static final int PIN_LENGTH = 4;

    /**
     * Generates a random Personal Identification Number (PIN).
     *
     * @return The generated PIN.
     */
    public static String generate() {
        int leftLimit = 0;
        int rightLimit = 9;

        Random random = new Random();
        StringBuilder buffer = new StringBuilder(PIN_LENGTH);

        for (int i = 0; i < PIN_LENGTH; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));

            buffer.append(randomLimitedInt);
        }
        return String.valueOf(buffer);
    }

    /**
     * Validates a Personal Identification Number (PIN).
     *
     * @param card_PIN The PIN to validate.
     * @return True if the PIN is valid (4 digits), false otherwise.
     */
    public static boolean isValid(String card_PIN) {
        return card_PIN.length() == 4;
    }
}
