package practice_16_3_homework_WritingTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculateFactorialTest {
    /**
     * Тесты для определения факториала числа:
     * positive cases:
     *  Обычные числа:
     *      factorial(1) → 1
     *      factorial(5) → 120
     *      factorial(7) → 5040
     * corner cases:
     *      factorial(0) → 1
     * negative cases:
     *      factorial(-3) → IllegalArgumentException
     */

    public static Stream<Arguments> validArraysAndExpectedMaximums() {
        return Stream.of(
                // positive cases
                Arguments.of(1, 1),
                Arguments.of(5, 120),
                Arguments.of(7, 5040),

                // corner cases
                Arguments.of(0, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("validArraysAndExpectedMaximums")
    public void shouldReturnCorrectFactorialForValidNumbers(int initialInt, int expextedResult) {
        CalculateFactorial calculateFactorial = new CalculateFactorial();

        int actualResult = calculateFactorial.factorial(initialInt);

        assertEquals(expextedResult, actualResult);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenNumberIsNegative() {
        CalculateFactorial calculateFactorial = new CalculateFactorial();

        assertThrows(
                IllegalArgumentException.class,
                () -> calculateFactorial.factorial(-3),
                "Passing a negative number should result in an IllegalArgumentException"
        );
    }
}
