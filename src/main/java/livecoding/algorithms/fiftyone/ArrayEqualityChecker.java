package livecoding.algorithms.fiftyone;

/*
 * Задача №51. Сравнить массивы
 *
 * Сравнить два массива
 * по содержимому и порядку элементов.
 */

import java.util.Arrays;

public class ArrayEqualityChecker {

    public ArrayEqualityChecker() {
    }

    public boolean arraysEqual(int[] a, int[] b) {
        return Arrays.equals(a, b); // Сравниваем длины и элементы по позициям
    }

    public static void main(String[] args) {
        ArrayEqualityChecker checker =
                new ArrayEqualityChecker();

        System.out.println(checker.arraysEqual(
                new int[]{1, 2, 3},
                new int[]{1, 2, 3}
        ));
        System.out.println(checker.arraysEqual(
                new int[]{1, 2, 3},
                new int[]{3, 2, 1}
        ));
        System.out.println(checker.arraysEqual(
                new int[]{1, 2},
                new int[]{1, 2, 3}
        ));
        System.out.println(checker.arraysEqual(
                new int[]{},
                new int[]{}
        ));
        System.out.println(
                checker.arraysEqual(null, null)
        );
    }
}
