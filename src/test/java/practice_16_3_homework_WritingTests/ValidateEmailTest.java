package practice_16_3_homework_WritingTests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateEmailTest {
    /**
     * Тесты для определения валидности e-mail:
     * positive cases:
     *  Корректные email:
     *      "test@example.com" → true
     *      "user.name@domain.co" → true
     *      "user.name@domain.co" → true
     * negative cases:
     *  Некорректные email:
     *      "bad@.com" → false
     *      "bad@.com" → false
     *      "bad@.com" → false
     *      "user@domain" → false
     *  Пустая строка: "" → false
     *  null: null → false
     */

    @ParameterizedTest
    @ValueSource(strings = {
            // positive cases
            // корректные email
            "test@example.com",
            "user.name@domain.co",
            "user.name@domain.co"
    })
    public void shouldReturnTrueForValidEmails(String initialString) {
        ValidateEmail validateEmail = new ValidateEmail();

        boolean actualResult = validateEmail.isValidEmail(initialString);

        assertTrue(actualResult);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {
            // negative cases
            // некорректные email
            "bad@.com",
            "bad@.com",
            "bad@.com",
            "user@domain",

            // пустая строка
            ""
    })
    public void shouldReturnFalseForInvalidEmails(String initialString) {
        ValidateEmail validateEmail = new ValidateEmail();

        boolean actualResult = validateEmail.isValidEmail(initialString);

        assertFalse(actualResult);
    }
}
