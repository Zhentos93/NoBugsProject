package livecoding.algorithms.sixteen;

/*
 * Задача №16. Удаление дубликатов из списка
 *
 * Удалить дубликаты из List<Integer>.
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DuplicateRemover {

    public DuplicateRemover() {
    }

    List<Integer> removeDuplicates(List<Integer> list) {
        return new ArrayList<>(     // Возвращаем список
                new HashSet<>(list) // Убираем дубликаты
        );
    }

    public static void main(String[] args) {
        DuplicateRemover remover = new DuplicateRemover();

        System.out.println(
                remover.removeDuplicates(List.of(1, 2, 2, 3, 1, 4))
        );
        System.out.println(remover.removeDuplicates(List.of(5, 5, 5)));
        System.out.println(remover.removeDuplicates(List.of()));
    }
}
