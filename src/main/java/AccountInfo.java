import java.util.List;
import java.util.ArrayList;

public class AccountInfo {
    String cardNumber;
    String card_PIN;
    double cardBalance;
    static List<AccountInfo> accounts = new ArrayList<>();

    public AccountInfo(String cardNumber, String card_PIN, double cardBalance) {
        this.cardNumber = cardNumber;
        this.card_PIN = card_PIN;
        this.cardBalance = cardBalance;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCard_PIN() {
        return card_PIN;
    }

    public double getCardBalance() {
        return cardBalance;
    }

    public static boolean checkAccountExistence(String cardNumber, String card_PIN) {

        if (accounts != null) {

            for (AccountInfo account : accounts) {

                if (account.getCardNumber().equals(cardNumber)
                        && account.getCard_PIN().equals(card_PIN)) return true;

            }

        }

        return false;
    }

    public static double getAccountBalance(String cardNumber, String card_PIN) {

        if (accounts != null) {

            for (AccountInfo account : accounts) {

                if (account.getCardNumber().equals(cardNumber)
                        && account.getCard_PIN().equals(card_PIN)) return account.getCardBalance();

            }

        }

        return -1;
    }

    public static void printAccountInfo() {
        int counter = 1;

        for (AccountInfo account : accounts) {
            System.out.println("#" + counter++
                    + " ; " + account.getCardNumber()
                    + " - " + account.getCard_PIN()
                    + " - " + account.getCardBalance());
        }
    }
}