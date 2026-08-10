package livecoding.algorithms.nine;

/*
 * Задача №9. Проверка анаграммы
 *
 * Проверить, являются ли две строки
 * анаграммами друг друга.
 */

import java.util.Arrays;

public class AnagramChecker {

    public AnagramChecker() {
    }

    boolean isAnagram(String a, String b) {
        char[] ca = a.toCharArray(); // Получаем символы первой строки
        char[] cb = b.toCharArray(); // Получаем символы второй строки

        Arrays.sort(ca); // Сортируем символы первой строки
        Arrays.sort(cb); // Сортируем символы второй строки

        return Arrays.equals(ca, cb); // Сравниваем содержимое массивов
    }

    public static void main(String[] args) {
        AnagramChecker checker = new AnagramChecker();

        System.out.println(checker.isAnagram("listen", "silent"));
        System.out.println(checker.isAnagram("Java", "avaJ"));
        System.out.println(checker.isAnagram("Test", "test"));
        System.out.println(checker.isAnagram("a b", "ab"));
    }
}
