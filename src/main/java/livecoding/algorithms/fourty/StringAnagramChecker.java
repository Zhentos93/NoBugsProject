package livecoding.algorithms.fourty;

/*
 * Задача №40. Проверка анаграмм
 *
 * Проверить, являются ли две строки анаграммами.
 */

import java.util.Arrays;

public class StringAnagramChecker {

    public StringAnagramChecker() {
    }

    public boolean isAnagram(String s, String t) {
        char[] a = s.toCharArray(); // Получаем символы первой строки
        char[] b = t.toCharArray(); // Получаем символы второй строки

        Arrays.sort(a); // Сортируем первый массив символов
        Arrays.sort(b); // Сортируем второй массив символов

        return Arrays.equals(a, b); // Сравниваем содержимое массивов
    }

    public static void main(String[] args) {
        StringAnagramChecker checker =
                new StringAnagramChecker();

        System.out.println(
                checker.isAnagram("listen", "silent")
        );
        System.out.println(
                checker.isAnagram("Java", "avaJ")
        );
        System.out.println(
                checker.isAnagram("Test", "test")
        );
        System.out.println(
                checker.isAnagram("a b", "ab")
        );
        System.out.println(
                checker.isAnagram("", "")
        );
    }
}
