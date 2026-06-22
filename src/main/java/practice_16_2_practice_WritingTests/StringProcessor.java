package practice_16_2_practice_WritingTests;

public class StringProcessor {

    // Метод для переворота строки
    public String reverse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return new StringBuilder(input).reverse().toString();
    }

    // Метод для проверки, является ли строка палиндромом
    // abba - палиндром
    // bba - не палиндром
    public boolean isPalindrome(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        String reversed = new StringBuilder(input).reverse().toString();
        return input.equals(reversed);
    }

    // Метод бросает исключение, если строка слишком короткая
    public String validateLength(String input, int minLength) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        };
        if (input.length() < minLength) {
            throw new IllegalArgumentException("Input cannot be at least " + minLength);
        }
        return input;
    };
}
