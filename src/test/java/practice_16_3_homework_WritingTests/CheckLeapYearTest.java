package practice_16_3_homework_WritingTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class CheckLeapYearTest {
    /**
     * Тесты для определения, является ли год високосным:
     * positive cases:
     *  Високосные годы:
     *      2020 → true
     *      2000 → true
     *      1600 → true
     * corner cases:
     *      0 → true
     *      4 → true
     *      400 → true
     * negative cases:
     *  Обычные невисокосные годы:
     *      2019 → false
     *      2021 → false
     *      2022 → false
     *  Года, делящиеся на 100, но не на 400:
     *      1900 → false
     *      2100 → false
     */

    @ParameterizedTest
    @ValueSource(ints = {
            // positive cases
            2020,
            2000,
            1600,

            // corner cases
            0,
            4,
            400
    })
    public void shouldReturnTrueForLeapYearsAndBoundaryValues(int initialYear) {
        CheckLeapYear checkLeapYear = new CheckLeapYear();

        boolean actualResult = checkLeapYear.isLeapYear(initialYear);

        assertTrue(actualResult);
    }

    @ParameterizedTest
    @ValueSource(ints = {
            // negative cases
            // обычные невисокосные годы
            2019,
            2021,
            2022,

            // года, делящиеся на 100
            1900,
            2100
    })
    public void shouldReturnFalseForNonLeapYears(int initialYear) {
        CheckLeapYear checkLeapYear = new CheckLeapYear();

        boolean actualResult = checkLeapYear.isLeapYear(initialYear);

        assertFalse(actualResult);
    }
}
