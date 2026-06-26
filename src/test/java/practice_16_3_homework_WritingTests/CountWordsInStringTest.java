package practice_16_3_homework_WritingTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CountWordsInStringTest {
    /**
     * Тесты для подсчёта количества слов в строке:
     * positive cases:
     *      обычная строка: "Hello world" → 2
     *      строка с лишними пробелами: " Java is awesome " → 3
     * corner cases:
     *      пустая строка: "" → 0
     *      строка с пробелами: " " → 0
     * negative cases:
     *      null строка: null → IllegalArgumentException
     */

    public static Stream<Arguments> validStringsAndExpectedWordCounts() {
        return Stream.of(
                //positive cases
                Arguments.of("Hello world", 2),
                Arguments.of(" Java is awesome ", 3),

                // corner cases
                Arguments.of("", 0),
                Arguments.of(" ", 0)

        );
    }

    @ParameterizedTest
    @MethodSource("validStringsAndExpectedWordCounts")
    public void shouldReturnCorrectWordCountForValidStrings(String initialString, int expectedResult) {
        CountWordsInString countWordsInString = new CountWordsInString();

        int actualResult = countWordsInString.countWords(initialString);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenInputIsNull() {
        CountWordsInString countWordsInString = new CountWordsInString();

        assertThrows(
                IllegalArgumentException.class,
                () -> countWordsInString.countWords(null),
                "Passing null should result in an IllegalArgumentException"
        );
    }
}
