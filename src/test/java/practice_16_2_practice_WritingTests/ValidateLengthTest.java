package practice_16_2_practice_WritingTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateLengthTest extends StringProcessorTest {
    /**
     * Тесты на валидацию длины
     *  позитивные сценарии
     *     "abba", 3 -> "abba"
     *     "abba", 4 -> "abba"
     *  негативный сценарий
     *     "abba", 5 -> IllegalArgumentException
     *  угловые сценарии
     *     "abba", 0 -> "abba"
     *     "", 0 -> ""
     *     "hello", -1 -> IllegalArgumentException
     */
    public static Stream<Arguments> stringForValidationPositiveCases() {
        return Stream.of(
                // позитивные сценарии
                Arguments.of("abba", 3),
                Arguments.of("abba", 4),
                // угловые сценарии
                Arguments.of("abba", 0),
                Arguments.of("", 0));

    }

    @ParameterizedTest
    @MethodSource("stringForValidationPositiveCases")
    public void userCanValidateStringWithLengthMoreOrEqualToMinValue(String initialString, int minValue) {
        String expectedResult = stringProcessor.validateLength(initialString, minValue);

        assertEquals(expectedResult, initialString);
    }

    public static Stream<Arguments> stringForValidationNegativeCases() {
        return Stream.of(
                // негативные сценарии
                Arguments.of("abba", 5),
                // угловые сценарии
                Arguments.of("hello", -1));

    }

    @ParameterizedTest
    @MethodSource("stringForValidationNegativeCases")
    public void userCanNotValidateStringWithLengthLessThanMinValue(String initialString, int minValue) {
        assertThrows(IllegalArgumentException.class, () -> {
            stringProcessor.validateLength(initialString, minValue);
        }, "Validation of String with length less than minValue should lead to IllegalArgumentException");
    }
}
