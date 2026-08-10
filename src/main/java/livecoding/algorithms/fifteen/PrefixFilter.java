package livecoding.algorithms.fifteen;

/*
 * Задача №15. Фильтрация списка строк по префиксу
 *
 * Оставить только строки, начинающиеся с A.
 */

import java.util.List;
import java.util.stream.Collectors;

public class PrefixFilter {

    public PrefixFilter() {
    }

    List<String> filter(List<String> list) {
        return list.stream()                     // Создаём поток строк
                .filter(s -> s.startsWith("A")) // Оставляем строки с A
                .collect(Collectors.toList());  // Собираем результат
    }

    public static void main(String[] args) {
        PrefixFilter prefixFilter = new PrefixFilter();

        List<String> words = List.of(
                "Apple", "Banana", "Apricot", " avocado", "A"
        );

        System.out.println(prefixFilter.filter(words));
        System.out.println(prefixFilter.filter(List.of()));
    }
}
