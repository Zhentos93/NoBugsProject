package practice_16_3_homework_WritingTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class CheckAnagramTest {
    /**
     * Тесты для проверки, являются ли две строки анаграммами:
     * positive cases:
     *  Анаграммы:
     *      "listen", "silent" → true
     * negative cases:
     *  Не анаграммы:
     *      "java", "python" → false
     *  null:
     *      null, "word" → false
     */

    @Test
    public void shouldReturnTrueForAnagrams() {
        CheckAnagram checkAnagram = new CheckAnagram();

        boolean actualResult = checkAnagram.isAnagram("listen", "silent");

        assertTrue(actualResult);
    }

    public static Stream<Arguments> nonAnagramsAndExpectedResults() {
        return Stream.of(
                // negative cases
                Arguments.of("java", "python"),
                Arguments.of(null, "word")
        );
    }

    @ParameterizedTest
    @MethodSource("nonAnagramsAndExpectedResults")
    public void shouldReturnFalseForNonAnagramsAndNullInput(String str1, String str2) {
        CheckAnagram checkAnagram = new CheckAnagram();

        boolean actualResult = checkAnagram.isAnagram(str1, str2);

        assertFalse(actualResult);
    }
}
