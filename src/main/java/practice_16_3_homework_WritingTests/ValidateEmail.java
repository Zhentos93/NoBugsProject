// Напишите тесты для метода, который проверяет, является ли строка валидным email:

/*Тесты должны проверять:
Корректные и некорректные email ("test@example.com", "bad@.com", "no-at-symbol")
null
*/

package practice_16_3_homework_WritingTests;

public class ValidateEmail {

    public boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
    }
}
