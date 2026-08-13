package livecoding.algorithms.fourtytwo;

/*
 * Задача №42. Реализация startsWith
 *
 * Проверить, начинается ли строка с префикса,
 * не используя стандартный метод startsWith().
 */

public class PrefixChecker {

    public PrefixChecker() {
    }

    public boolean startsWith(String word, String prefix) {
        if (prefix.length() > word.length()) { // Префикс длиннее строки
            return false;
        }

        for (int i = 0; i < prefix.length(); i++) {
            if (word.charAt(i) != prefix.charAt(i)) {
                return false; // Символы на одной позиции различаются
            }
        }

        return true; // Все символы префикса совпали
    }

    public static void main(String[] args) {
        PrefixChecker checker = new PrefixChecker();

        System.out.println(
                checker.startsWith("Java", "Ja")
        );
        System.out.println(
                checker.startsWith("Java", "Java")
        );
        System.out.println(
                checker.startsWith("Java", "java")
        );
        System.out.println(
                checker.startsWith("Java", "JavaScript")
        );
        System.out.println(
                checker.startsWith("Java", "")
        );
        System.out.println(
                checker.startsWith("", "")
        );
    }
}
