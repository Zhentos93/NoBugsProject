package practice_16_3_homework_WritingTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class FilterEvenNumbersTest {
    /**
     * Тесты фильтрации списка чисел (должны оставаться только чётные):
     * positive cases:
     *  Обычный список:
     *      [1, 2, 3, 4, 5, 6] → [2, 4, 6]
     *  Список без чётных чисел:
     *      [1, 3, 5] → []
     * corner cases:
     *  Пустой список:
     *      [] -> []
     */

    public static Stream<Arguments> validListsAndExpectedEvenNumbers() {
        return Stream.of(
                // positive cases
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(2, 4, 6)),
                Arguments.of(List.of(1, 3, 5), List.of()),

                // corner cases
                Arguments.of(List.of(), List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("validListsAndExpectedEvenNumbers")
    public void shouldReturnOnlyEvenNumbersForValidLists(List<Integer> initial, List<Integer> expectedResult) {
        FilterEvenNumbers filterEvenNumbers = new FilterEvenNumbers();

        List<Integer> actualResult = filterEvenNumbers.filterEvenNumbers(initial);

        assertEquals(expectedResult, actualResult);
    }
}
