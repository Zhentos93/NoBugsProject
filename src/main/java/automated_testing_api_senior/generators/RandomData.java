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
}
