// Напишите тесты на метод, который сортирует список строк по длине

/*
Тесты:
        [ "Java", "C", "Python" ] → [ "C", "Java", "Python" ]
        Одинаковые длины ([ "aa", "bb", "cc" ])
        Пустой список
*/

package practice_16_3_homework_WritingTests;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortStringsByLength {

    public List<String> sortByLength(List<String> words) {
        return words
                .stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
    }
}
