// Напишите тесты на метод, который проверяет, являются ли две строки анаграммами

/*
Тесты:
        "listen", "silent" → true
        "java", "python" → false
        null → false
*/

package practice_16_3_homework_WritingTests;

import java.util.Arrays;

public class CheckAnagram {

    public boolean isAnagram(String str1, String str2) {
        if (str1 == null || str2 == null) return false;
        char[] arr1 = str1.toLowerCase().replaceAll("\\s", "").toCharArray();
        char[] arr2 = str2.toLowerCase().replaceAll("\\s", "").toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        return Arrays.equals(arr1, arr2);
    }
}
