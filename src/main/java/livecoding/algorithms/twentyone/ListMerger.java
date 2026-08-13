package livecoding.algorithms.twentyone;

/*
 * Задача №21. Слияние двух списков
 *
 * Объединить два списка в один.
 */

import java.util.ArrayList;
import java.util.List;

public class ListMerger {

    public ListMerger() {
    }

    List<String> merge(List<String> a, List<String> b) {
        List<String> result = new ArrayList<>(a); // Копируем первый список
        result.addAll(b);                         // Добавляем второй список

        return result; // Возвращаем новый объединённый список
    }

    public static void main(String[] args) {
        ListMerger merger = new ListMerger();

        List<String> first = List.of("Java", "Spring");
        List<String> second = List.of("SQL", "Docker");

        System.out.println(merger.merge(first, second));
        System.out.println(merger.merge(List.of(), second));
        System.out.println(merger.merge(first, List.of()));
        System.out.println(merger.merge(List.of(), List.of()));
    }
}
