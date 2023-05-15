public class LuhnAlgorithm {
    public static int findChecksum(long fifteenDigitNumber) {
        int length = 15;

        // Array of 15 digits
        int[] arrayOfDigits = new int[length];
        int i = arrayOfDigits.length - 1;

        while (fifteenDigitNumber > 0) {
            int separateDigit = (int) (fifteenDigitNumber % 10);
            fifteenDigitNumber /= 10;

            if (i % 2 == 0) {
                //  Multiplying odd digits by 2 and subtracting 9 to numbers over 9
                arrayOfDigits[i] = (2 * separateDigit) > 9 ? (2 * separateDigit) - 9 : (2 * separateDigit);
            } else {
                arrayOfDigits[i] = separateDigit;
            }

            i--;
        }

        int controlNumber = 0;

        for (int arrayOfDigit : arrayOfDigits) {
            controlNumber += arrayOfDigit;
        }

        return controlNumber;
    }
}
