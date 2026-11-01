package automated_testing.generators;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {
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
}
