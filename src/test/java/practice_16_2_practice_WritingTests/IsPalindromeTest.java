package practice_16_2_practice_WritingTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class IsPalindromeTest extends StringProcessorTest {
    /**
     * Тесты для проверки, является ли палиндромом:
     * positive cases:
     *      четное кол-во: "abba" -> true
     *      нечетное кол-во: "hah" -> true
     * corner cases:
     *      "a" -> true
     *      "" -> true
     * negative cases:
     *      "john" -> false
     *      null -> IllegalArgumentException
     */

    @ParameterizedTest
    @ValueSource(strings = {
            // positive cases
            "abba",
            "hah",

            // corner cases
            "a", ""
    })
    public void userCanCheckIfValidStringIsPalindrome(String initialString) {
        boolean actualResult = stringProcessor.isPalindrome(initialString);

        assertTrue(actualResult);
    }

    @Test
    public void userCanCheckIfValidStringIsNotPalindrome() {
        String initialString = "john";

        boolean actualResult = stringProcessor.isPalindrome(initialString);

        assertFalse(actualResult);
    }

    @Test
    public void userCannotCheckIfNullStringIsPalindrome() {
        assertThrows(
                IllegalArgumentException.class,
                () -> stringProcessor.reverse(null),
                "Checking if Null string is palindrome should lead to IllegalArgumentException"
        );
    }
}
