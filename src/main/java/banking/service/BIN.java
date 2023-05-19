package banking.service;

/**
 * The BIN class represents the Bank Identification Number (BIN) used in the banking system.
 * It provides a method to retrieve the BIN value.
 */
public class BIN {
    static final String BIN = "400000";

    /**
     * Retrieves the Bank Identification Number (BIN).
     *
     * @return The BIN value.
     */
    public static String get() {
        return BIN;
    }
}
