package livecoding.algorithms.twentyone;

/*
 * Задача №21. Слияние двух списков
 *
 * Объединить два списка в один.
 */

// Ошибки
/*
 * Код до подсказки не присылался.
 * Для начала решения потребовался разбор:
 *
 * 1. new ArrayList<>(a) — изменяемая копия первого списка.
 * 2. result.addAll(b) — добавление всех элементов второго.
 */

import java.util.ArrayList;
import java.util.List;

public class ListMerge {
    public ListMerge() {
    }

    public List<String> merge(List<String> a, List<String> b) {
        List<String> result = new ArrayList<>(a);

        result.addAll(b);

        return result;
    }

    public static void main(String[] args) {
        ListMerge listMerge = new ListMerge();

        System.out.println(listMerge.merge(List.of("Java", "Spring"), List.of("SQL", "Docker")));
        System.out.println(listMerge.merge(List.of(), List.of("SQL", "Docker")));
        System.out.println(listMerge.merge(List.of("Java", "Spring"), List.of()));
        System.out.println(listMerge.merge(List.of(), List.of()));
    }
}
