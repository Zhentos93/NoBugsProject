package practice_16_3_homework_WritingTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SortStringsByLengthTest {
    /**
     * Тесты для сортировки списка строк по длине:
     * positive cases:
     *  Обычный список:
     *      ["Java", "C", "Python"] → ["C", "Java", "Python"]
     *  Список с одинаковыми длинами:
     *      ["aa", "bb", "cc"] → "aa", "bb", "cc"]
     * corner cases:
     *  Пустой список:
     *      [] -> []
     */

    public static Stream<Arguments> validListsAndExpectedSortedStrings() {
        return Stream.of(
                // positive cases
                Arguments.of(List.of("Java", "C", "Python"), List.of("C", "Java", "Python")),
                Arguments.of(List.of("aa", "bb", "cc"), List.of("aa", "bb", "cc")),

                // corner cases
                Arguments.of(List.of(), List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("validListsAndExpectedSortedStrings")
    public void shouldSortStringsByLengthForValidLists(List<String> initial, List<String> expectedResult) {
        SortStringsByLength sortStringsByLength = new SortStringsByLength();

        List<String> actualResult = sortStringsByLength.sortByLength(initial);

        assertEquals(expectedResult, actualResult);
    }
}
