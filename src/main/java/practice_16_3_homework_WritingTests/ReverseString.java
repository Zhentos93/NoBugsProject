// Напишите тесты для метода, который переворачивает строку

/*
Тесты должны проверять:
Обычные строки
Пустую строку
null (должно возвращаться null)
*/

package practice_16_3_homework_WritingTests;

public class ReverseString {

    public String reverse(String input) {
        if (input == null) return null;
        return new StringBuilder(input).reverse().toString();
    }
}
