package livecoding.algorithms.twelve;

/*
 * Задача №12. Проверка уникальности элементов
 *
 * Проверить, все ли элементы в массиве уникальны.
 */

import java.util.HashSet;
import java.util.Set;

public class UniqueElementsChecker {

    public UniqueElementsChecker() {
    }

    boolean allUnique(int[] arr) {
        Set<Integer> set = new HashSet<>(); // Храним встреченные числа

        for (int num : arr) { // По очереди проверяем элементы массива
            if (!set.add(num)) { // Число уже встречалось
                return false;
            }
        }

        return true; // Повторяющиеся элементы не найдены
    }

    public static void main(String[] args) {
        UniqueElementsChecker checker = new UniqueElementsChecker();

        System.out.println(checker.allUnique(new int[]{1, 2, 3, 4}));
        System.out.println(checker.allUnique(new int[]{1, 2, 3, 1}));
        System.out.println(checker.allUnique(new int[]{}));
    }
}
