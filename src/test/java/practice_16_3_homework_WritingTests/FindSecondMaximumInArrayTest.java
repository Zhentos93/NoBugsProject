package practice_16_3_homework_WritingTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FindSecondMaximumInArrayTest {
    /**
     * Тесты для определения второго максимального числа в массиве:
     * positive cases:
     *      обычный массив: [3, 5, 7, 2] → 5
     * negative cases:
     *      массив с одинаковыми числами: [4, 4, 4, 4] → NoSuchElementException
     *      один элемент: [8] → NoSuchElementException (ФР: IllegalArgumentException)
     *      пустой массив: [] → NoSuchElementException (ФР: IllegalArgumentException)
     */

    @Test
    public void shouldReturnSecondMaximumValueForValidArray() {
        int[] numbers = new int[]{3, 5, 7, 2};
        int expectedResult = 5;

        FindSecondMaximumInArray findSecondMaximumInArray = new FindSecondMaximumInArray();

        int actualResult = findSecondMaximumInArray.findSecondMax(numbers);

        assertEquals(expectedResult, actualResult);
    }


    public static Stream<Arguments> arraysWithoutSecondMaximum() {
        return Stream.of(
                // negative cases
                Arguments.of(new int[]{4, 4, 4, 4}),
                Arguments.of(new int[]{8}),
                Arguments.of(new int[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("arraysWithoutSecondMaximum")
    public void shouldThrowNoSuchElementExceptionForInvalidArrays(int[] numbers)
    {
        FindSecondMaximumInArray findSecondMaximumInArray = new FindSecondMaximumInArray();

        assertThrows(
                NoSuchElementException.class,
                () -> findSecondMaximumInArray.findSecondMax(numbers),
                "Passing a negative number should result in an IllegalArgumentException");
    }
}
