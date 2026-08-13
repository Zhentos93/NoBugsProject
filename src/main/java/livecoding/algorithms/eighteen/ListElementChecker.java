package livecoding.algorithms.eighteen;

/*
 * Задача №18. Проверка наличия элемента в списке
 *
 * Проверить, содержит ли список заданное число.
 */

import java.util.List;

public class ListElementChecker {

    public ListElementChecker() {
    }

    boolean contains(List<Integer> list, int target) {
        return list.contains(target); // Проверяем наличие числа в списке
    }

    public static void main(String[] args) {
        ListElementChecker checker = new ListElementChecker();

        System.out.println(checker.contains(List.of(1, 2, 3, 4), 3));
        System.out.println(checker.contains(List.of(1, 2, 3, 4), 7));
        System.out.println(checker.contains(List.of(), 1));
        System.out.println(checker.contains(List.of(5, 5, 5), 5));
    }
}
