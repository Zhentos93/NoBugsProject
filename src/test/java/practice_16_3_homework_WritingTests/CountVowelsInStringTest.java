package practice_16_3_homework_WritingTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CountVowelsInStringTest {
    /**
     * Тесты для определения количества гласных букв в строке:
     * positive cases:
     *  Разные строки с гласными:
     *      "hello" -> 2
     *      "java" -> 2
     *      "AEIOU" -> 5
     * corner cases:
     *      "" -> 0
     *      "sdfk" -> 0
     *      "a" -> 1
     *      "b" -> 0
     * negative cases:
     *      null -> IllegalArgumentException
     */

    public static Stream<Arguments> validStringsAndExpectedVowelCounts() {
        return Stream.of(
                // positive cases
                Arguments.of("hello", 2),
                Arguments.of("java", 2),
                Arguments.of("AEIOU", 5),

                // corner cases
                Arguments.of("", 0),
                Arguments.of("sdfk", 0),
                Arguments.of("a", 1),
                Arguments.of("b", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("validStringsAndExpectedVowelCounts")
    public void shouldReturnCorrectVowelCountForValidStrings(String initialString, int expectedResult) {
        CountVowelsInString countVowelsInString = new CountVowelsInString();

        int actualResult = countVowelsInString.countVowels(initialString);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenInputIsNull() {
        CountVowelsInString countVowelsInString = new CountVowelsInString();

        assertThrows(
                IllegalArgumentException.class,
                () -> countVowelsInString.countVowels(null),
                "Passing null should result in an IllegalArgumentException"
        );
    }
}
