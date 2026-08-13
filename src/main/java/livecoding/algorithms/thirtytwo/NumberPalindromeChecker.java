package livecoding.algorithms.thirtytwo;

/*
 * Задача №32. Проверка палиндрома числа
 *
 * Проверить, является ли число палиндромом.
 */

public class NumberPalindromeChecker {

    public NumberPalindromeChecker() {
    }

    boolean isPalindrome(int n) {
        int rev = 0;
        int original = n; // Сохраняем исходное число

        while (n > 0) { // Обрабатываем цифры справа налево
            rev = rev * 10 + n % 10; // Добавляем последнюю цифру
            n /= 10;                 // Удаляем последнюю цифру
        }

        return original == rev; // Сравниваем исходное и развёрнутое числа
    }

    public static void main(String[] args) {
        NumberPalindromeChecker checker =
                new NumberPalindromeChecker();

        System.out.println(checker.isPalindrome(121));
        System.out.println(checker.isPalindrome(12321));
        System.out.println(checker.isPalindrome(123));
        System.out.println(checker.isPalindrome(0));
        System.out.println(checker.isPalindrome(10));
        System.out.println(checker.isPalindrome(-121));
    }
}
