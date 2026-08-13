package livecoding.algorithms.twentynine;

/*
 * Задача №29. Сортировка строк по длине
 *
 * Отсортировать список строк по длине слов.
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StringLengthSorter {

    public StringLengthSorter() {
    }

    void sortByLength(List<String> list) {
        list.sort(Comparator.comparingInt(String::length));
    }

    public static void main(String[] args) {
        StringLengthSorter sorter = new StringLengthSorter();

        List<String> words = new ArrayList<>(
                List.of("Java", "C", "Python", "Go", "SQL")
        );

        sorter.sortByLength(words);
        System.out.println(words);

        List<String> emptyList = new ArrayList<>();
        sorter.sortByLength(emptyList);
        System.out.println(emptyList);
    }
}
