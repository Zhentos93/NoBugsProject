package livecoding.algorithms.seventeen;

/*
 * Задача №17. Фильтрация нечётных чисел
 *
 * Оставить в списке только нечётные числа.
 */

import java.util.List;
import java.util.stream.Collectors;

public class OddNumberFilter {

    public OddNumberFilter() {
    }

    List<Integer> filterOdds(List<Integer> list) {
        return list.stream()                    // Создаём поток чисел
                .filter(x -> x % 2 != 0)       // Оставляем нечётные
                .collect(Collectors.toList()); // Собираем результат
    }

    public static void main(String[] args) {
        OddNumberFilter filter = new OddNumberFilter();

        System.out.println(filter.filterOdds(List.of(1, 2, 3, 4, 5)));
        System.out.println(filter.filterOdds(List.of(-3, -2, -1, 0, 2)));
        System.out.println(filter.filterOdds(List.of(2, 4, 6)));
        System.out.println(filter.filterOdds(List.of()));
    }
}
