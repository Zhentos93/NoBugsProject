package automated_testing_api_senior.generators;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class RandomData {
    private static final Random RANDOM = new Random();
    private static final double MIN_DEPOSIT_AMOUNT = 0.01;
    private static final double MAX_DEPOSIT_AMOUNT = 5000.0;

    private RandomData() {
    }

    public static String getUsername() {
        return RandomStringUtils.secure().nextAlphabetic(10);
    }

    public static String getPassword() {
        return RandomStringUtils.secure().nextAlphabetic(3).toUpperCase() +
                RandomStringUtils.secure().nextAlphabetic(5).toLowerCase() +
                RandomStringUtils.secure().nextNumeric(3) + "$";
    }

    public static double getValidDepositAmount() {
        return getAmount(MIN_DEPOSIT_AMOUNT, MAX_DEPOSIT_AMOUNT);
    }

    public static double getAmount(double minInclusive, double maxInclusive) {
        if (minInclusive > maxInclusive) {
            throw new IllegalArgumentException("Minimum amount cannot be greater than maximum amount");
        }

        double amount = minInclusive +
                (maxInclusive - minInclusive) * RANDOM.nextDouble();

        return Math.round(amount * 1000.0) / 1000.0;
    }

    public static long getNonExistentAccountId() {
        return 1_000_000_000L + RANDOM.nextInt(1_000_000_000);
    }

    public static double[] getDepositAmountsForTotal(double totalAmount) {
        double firstMin = Math.max(MIN_DEPOSIT_AMOUNT, totalAmount - 2 * MAX_DEPOSIT_AMOUNT);

        double firstMax = Math.min(MAX_DEPOSIT_AMOUNT, totalAmount - 2 * MIN_DEPOSIT_AMOUNT);

        double firstDepositAmount = getAmount(firstMin, firstMax);

        double remainingAmount = roundAmount(totalAmount - firstDepositAmount);

        double secondMin = Math.max(MIN_DEPOSIT_AMOUNT, remainingAmount - MAX_DEPOSIT_AMOUNT);

        double secondMax = Math.min(MAX_DEPOSIT_AMOUNT, remainingAmount - MIN_DEPOSIT_AMOUNT);

        double secondDepositAmount = getAmount(secondMin, secondMax);

        double thirdDepositAmount = roundAmount(remainingAmount - secondDepositAmount);

        return new double[]{
                firstDepositAmount, secondDepositAmount, thirdDepositAmount
        };
    }

    private static double roundAmount(double amount) {
        return Math.round(amount * 1000.0) / 1000.0;
    }

    public static String getFullName() {
        return getNameWord() + " " + getNameWord();
    }

    private static final String SPECIAL_CHARACTERS = "!@#$%^&*";

    private static String getNameWord() {
        int wordLength = 3 + RANDOM.nextInt(8);

        return RandomStringUtils.secure().nextAlphabetic(wordLength);
    }

    public static String getOneWordName() {
        return getNameWord();
    }

    public static String getThreeWordName() {
        return getNameWord() + " " + getNameWord() + " " + getNameWord();
    }

    public static String getNameWithDigit() {
        return getFullName() + RandomStringUtils.secure().nextNumeric(1);
    }

    public static String getNameWithSpecialCharacter() {
        char specialCharacter = SPECIAL_CHARACTERS.charAt(RANDOM.nextInt(SPECIAL_CHARACTERS.length()));

        return getFullName() + specialCharacter;
    }
}