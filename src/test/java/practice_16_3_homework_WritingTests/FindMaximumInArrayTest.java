package practice_16_3_homework_WritingTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FindMaximumInArrayTest {
    /**
     * Тесты для определения количества гласных букв в строке:
     * positive cases:
     *      [3, 5, 7, 2] → 7
     *      [-3, -5, -7, -2] → -2
     * corner cases:
     *      [1] → 1
     * negative cases:
     *      [] → NoSuchElementException
     *      null → NullPointerException
     */

    public static Stream<Arguments> validArraysAndExpectedMaximums() {
        return Stream.of(
                // positive cases
                Arguments.of(new int[]{3, 5, 7, 2}, 7),
                Arguments.of(new int[]{-3, -5, -7, -2}, -2),

                // corner cases
                Arguments.of(new int[]{1}, 1)
        );
    }

    @ParameterizedTest
    @MethodSource("validArraysAndExpectedMaximums")
    public void shouldReturnMaximumValueForValidArrays(int[] numbers, int expectedResult) {
        FindMaximumInArray findMaximumInArray = new FindMaximumInArray();

        int actualResult = findMaximumInArray.findMax(numbers);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenArrayIsEmpty() {
        FindMaximumInArray findMaximumInArray = new FindMaximumInArray();

        assertThrows(
                NoSuchElementException.class,
                () -> findMaximumInArray.findMax(new int[]{}),
                "Passing an empty array should result in a NoSuchElementException"
        );
    }

    @Test
    public void shouldIllegalArgumentExceptionWhenInputIsNull() {
        FindMaximumInArray findMaximumInArray = new FindMaximumInArray();

        assertThrows(
                IllegalArgumentException.class,
                () -> findMaximumInArray.findMax(null),
                "Passing null should result in an IllegalArgumentException"
        );
    }
}
