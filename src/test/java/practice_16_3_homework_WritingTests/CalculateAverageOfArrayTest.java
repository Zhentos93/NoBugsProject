package practice_16_3_homework_WritingTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CalculateAverageOfArrayTest {
    /**
     * Тесты поиска среднего значения в массиве чисел:
     * positive cases:
     *  Обычный массив:
     *      [1, 2, 3, 4, 5] → 3.0
     *  Массив с одним элементом:
     *      [10] → 10.0
     * negative cases:
     *  Пустой массив:
     *      [] -> NoSuchElementException
     */

    public static Stream<Arguments> validArraysToCalculateAverage() {
        return Stream.of(
                // positive cases
                Arguments.of(new int[]{1, 2, 3, 4, 5}, 3.0),
                Arguments.of(new int[]{10}, 10.0)
        );
    }

    @ParameterizedTest
    @MethodSource("validArraysToCalculateAverage")
    public void shouldCalculateAverageForValidArrays(int[] numbers, double expectedResult) {
        CalculateAverageOfArray calculateAverageOfArray = new CalculateAverageOfArray();

        double actualResult = calculateAverageOfArray.findAverage(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldThrowExceptionForInvalidArrays() {
        int[] numbers = new int[]{};

        CalculateAverageOfArray calculateAverageOfArray = new CalculateAverageOfArray();

        assertThrows(
                NoSuchElementException.class,
                () -> calculateAverageOfArray.findAverage(new int[]{}),
                "Expected NoSuchElementException for an empty array, but no exception was thrown."
        );
    }
}
