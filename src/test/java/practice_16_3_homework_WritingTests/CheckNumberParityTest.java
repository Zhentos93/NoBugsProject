package practice_16_3_homework_WritingTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CheckNumberParityTest {
    /**
     * Тесты для определения, является ли число чётным:
     * positive cases:
     *      четное число: 4 -> true
     *      четное отрицательное число: -4 -> true
     * corner cases:
     *      0 -> true
     * negative cases:
     *      нечетное число: 3 -> false
     *      нечетное отрицательное число: -5 -> false
     */

    @ParameterizedTest
    @ValueSource(ints = {
            // positive cases
            4,
            -4,

            // corner cases
            0
    })
    public void shouldReturnTrueWhenNumberIsEven(int initialInt) {
        CheckNumberParity checkNumberParity = new CheckNumberParity();

        boolean actualResult = checkNumberParity.isEven(initialInt);

        assertTrue(actualResult);
    }

    @ParameterizedTest
    @ValueSource(ints = {
            // negative cases
            3,
            -5
    })
    public void shouldReturnFalseWhenNumberIsOdd(int initialInt) {
        CheckNumberParity checkNumberParity = new CheckNumberParity();

        boolean actualResult = checkNumberParity.isEven(initialInt);

        assertFalse(actualResult);
    }
}
