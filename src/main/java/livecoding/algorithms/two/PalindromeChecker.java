package livecoding.algorithms.two;

/*
 * Задача №2. Проверка палиндрома
 *
 * Проверить, является ли строка палиндромом:
 * одинаково ли она читается в обе стороны.
 */

public class PalindromeChecker {

    public PalindromeChecker() {
    }

    boolean isPalindrome(String s) {
        String clean = s.replaceAll("\\s+", "").toLowerCase(); // Убираем пробелы и регистр

        return new StringBuilder(clean)
                .reverse()
                .toString()
                .equals(clean); // Сравниваем строки по содержимому
    }

    public static void main(String[] args) {
        PalindromeChecker checker = new PalindromeChecker();

        System.out.println(checker.isPalindrome("А роза упала на лапу Азора")); // true
        System.out.println(checker.isPalindrome("топот"));                      // true
        System.out.println(checker.isPalindrome("Java"));                       // false
        System.out.println(checker.isPalindrome(""));                           // true
    }
}
