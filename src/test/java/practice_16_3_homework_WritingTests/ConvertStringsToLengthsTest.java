package practice_16_3_homework_WritingTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ConvertStringsToLengthsTest {
    /**
     * Тесты для преобразования списка строк в список их длин:
     * positive cases:
     *  Обычный список:
     *      ["Java", "C++", "Go"] → [4, 3, 2]
     * corner cases:
     *  Пустой список:
     *      [] -> []
     */

    public static Stream<Arguments> validListsAndExpectedSortedStrings() {
        return Stream.of(
                // positive cases
                Arguments.of(List.of("Java", "C++", "Go"), List.of(4, 3, 2)),

                // corner cases
                Arguments.of(List.of(), List.of())
        );
    }

    @ParameterizedTest
    @MethodSource("validListsAndExpectedSortedStrings")
    public void shouldReturnStringLengthsForValidLists(List<String> initial, List<Integer> expectedResult) {
        ConvertStringsToLengths convertStringsToLengths = new ConvertStringsToLengths();

        List<Integer> actualResult = convertStringsToLengths.mapToLengths(initial);

        assertEquals(expectedResult, actualResult);
    }
}
