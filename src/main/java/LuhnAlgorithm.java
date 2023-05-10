public class LuhnAlgorithm {
    public static int findChecksum(String fifteenDigitNumber) {
        int[] arrayOfDigits = new int[fifteenDigitNumber.length()];

        for (int i = 0; i < arrayOfDigits.length; i++) {
            int separateDigit = Character.getNumericValue(fifteenDigitNumber.charAt(i));

            if (i % 2 == 0) {
                //  Multiplying odd digits by 2 and subtracting 9 to numbers over 9
                arrayOfDigits[i] = (2 * separateDigit) > 9 ? (2 * separateDigit) - 9 : (2 * separateDigit);
            } else {
                arrayOfDigits[i] = separateDigit;
            }
        }

        int controlNumber = 0;

        for (int arrayOfDigit : arrayOfDigits) {
            controlNumber += arrayOfDigit;
        }

        return controlNumber;
    }
}
