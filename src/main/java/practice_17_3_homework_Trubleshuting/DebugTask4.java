// Код должен проверить, является ли строка палиндромом, но выбрасывает NullPointerException.

package practice_17_3_homework_Trubleshuting;

public class DebugTask4 {
    public static void main(String[] args) {
        System.out.println(isPalindrome("abba"));
    }
    public static boolean isPalindrome(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }
}

// было System.out.println(isPalindrome(null));
