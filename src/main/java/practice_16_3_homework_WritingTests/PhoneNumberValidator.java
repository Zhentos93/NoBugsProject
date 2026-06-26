// Напишите тесты для метода, который проверяет валидность телефонного номера:

/*
Тесты должны проверять:
Корректные номера ("+1 1234567890")
Некорректные номера ("12345", "invalid")
*/

package practice_16_3_homework_WritingTests;

public class PhoneNumberValidator {
    public boolean isValidPhoneNumber(String phone) {
        return phone.matches("\\+\\d{1,3} \\d{10}");
    }
}
