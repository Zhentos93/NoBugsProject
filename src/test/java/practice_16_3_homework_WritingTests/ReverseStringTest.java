package practice_16_3_homework_WritingTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReverseStringTest {
    /**
     * Тесты для переворота строки:
     * positive cases:
     *      "hello" → "olleh"
     *      "Java" → "avaJ"
     *      "12345" → "54321"
     * corner cases:
     *      "" → ""
     *      "a" → "a"
     *      null → null
     */

    public static Stream<Arguments> validStringsToReverse() {
        return Stream.of(
                // positive cases
                Arguments.of("hello", "olleh"),
                Arguments.of("Java", "avaJ"),
                Arguments.of("12345", "54321"),

                // corner cases
                Arguments.of("", ""),
                Arguments.of("a", "a"),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("validStringsToReverse")
    public void shouldReturnReversedString(String initialString, String expectedString) {
        ReverseString reverseString = new ReverseString();

        String reversedString = reverseString.reverse(initialString);

        assertEquals(expectedString, reversedString,
                "String reversed incorrectly!!!");
    }
}
