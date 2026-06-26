package practice_16_2_practice_WritingTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Class StringProcessor, method reverse")
public class ReverseStringTest extends StringProcessorTest {
    /**
     * Тесты для переворота строки:
     * positive cases:
     *     "sasha" -> "ahsas"
     * corner cases:
     *     "" -> ""
     *     "a" -> "a"
     * negative cases:
     *     null -> IllegalArgument Exception
     */

    public static Stream<Arguments> validStringsToReverse() {
        return Stream.of(
                // positive cases
                Arguments.of("sasha", "ahsas"),

                // corner cases:
                Arguments.of("", ""),
                Arguments.of("a", "a")
        );
    }

    @ParameterizedTest
    @MethodSource("validStringsToReverse")
    public void userCanReverseValidString(String initialString, String expectedString) {
        String reversedString = stringProcessor.reverse(initialString);

        assertEquals(expectedString, reversedString,
                "String reversed incorrectly!!!");
    }

    @Test
    public void userCannotReverseNullString() {
        assertThrows(
                IllegalArgumentException.class,
                () -> stringProcessor.reverse(null),
                "Reversing of null string should lead to IllegalArgumentException"
        );
    }
}
