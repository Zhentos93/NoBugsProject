package livecoding.algorithms.eighteen;

/*
 * Задача №18. Проверка наличия элемента в списке
 *
 * Проверить, содержит ли список заданное число.
 */

// Ошибки
/*
 * Ошибки в задаче №18 — проверка наличия элемента в списке:
 *
 * 1. Список создавался с недопустимым синтаксисом:
 *    new ArrayList<>(){1, 2, 3, 4}
 *
 *    По примеру решения нужно:
 *    List.of(1, 2, 3, 4)
 *
 * Сам метод contains() написан без ошибок.
 */

import java.util.List;

public class ListElement {
    public ListElement() {
    }

    public boolean contains(List<Integer> list, int target) {
        return list.contains(target);
    }

    public static void main(String[] args) {
        ListElement listElement = new ListElement();

        System.out.println(listElement.contains(List.of(1, 2, 3, 4), 4));
        System.out.println(listElement.contains(List.of(1, 2, 3, 4), 7));
        System.out.println(listElement.contains(List.of(), 1));
        System.out.println(listElement.contains(List.of(5, 5, 5), 5));
    }
}
