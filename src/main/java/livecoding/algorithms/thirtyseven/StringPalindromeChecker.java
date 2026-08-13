package livecoding.algorithms.thirtyseven;

/*
 * Задача №37. Палиндром
 *
 * Проверить, является ли строка палиндромом
 * без учёта регистра и пробелов.
 */

public class StringPalindromeChecker {

    public StringPalindromeChecker() {
    }

    public boolean isPalindrome(String s) {
        String clean = s.replaceAll("\\s+", "").toLowerCase();

        return clean.equals(
                new StringBuilder(clean).reverse().toString()
        );
    }

    public static void main(String[] args) {
        StringPalindromeChecker checker =
                new StringPalindromeChecker();

        System.out.println(
                checker.isPalindrome("А роза упала на лапу Азора")
        );
        System.out.println(
                checker.isPalindrome("Never odd or even")
        );
        System.out.println(checker.isPalindrome("Java"));
        System.out.println(checker.isPalindrome(""));
        System.out.println(checker.isPalindrome("A"));
    }
}
