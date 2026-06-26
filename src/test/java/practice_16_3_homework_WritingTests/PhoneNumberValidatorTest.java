package practice_16_3_homework_WritingTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class PhoneNumberValidatorTest {
    /**
     * Тесты для проверки валидности номера телефона:
     * positive cases:
     *  Корректные номера:
     *      "+1 1234567890" → true
     *      "+44 9876543210" → true
     *      "+999 1111111111" → true
     * negative cases:
     *  Некорректные номера:
     *      "12345" → false
     *      "invalid" → false
     *      "+1 abcdefghij" → false
     *      "+1234 1234567890" (слишком длинный код страны) → false
     *      "+1 123" (недостаточно цифр) → false
     *      "+1 123" (пустая строка) → false
     *  null:
     *      null -> IllegalArgumentException
     */

    @ParameterizedTest
    @ValueSource(strings = {
            // positive cases
            "+1 1234567890",
            "+44 9876543210",
            "+999 1111111111"
    })
    public void shouldReturnTrueForValidPhoneNumbers(String initialString) {
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

        boolean actualResult = phoneNumberValidator.isValidPhoneNumber(initialString);

        assertTrue(actualResult);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            // negative cases
            "12345",
            "invalid",
            "+1 abcdefghij",
            "+1234 1234567890",
            "+1234 1234567890",
            "+1 123",
            ""
    })
    public void shouldReturnFalseForInvalidPhoneNumbers(String initialString) {
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

        boolean actualResult = phoneNumberValidator.isValidPhoneNumber(initialString);

        assertFalse(actualResult);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenInputIsNull() {
        PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

        assertThrows(
                IllegalArgumentException.class,
                () -> phoneNumberValidator.isValidPhoneNumber(null),
                "Passing null should result in an IllegalArgumentException"
        );
    }
}
