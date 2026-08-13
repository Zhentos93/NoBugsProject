package livecoding.algorithms.thirtynine;

/*
 * Задача №39. Объединение отсортированных массивов
 *
 * Объединить два отсортированных массива
 * в один отсортированный массив.
 */

import java.util.Arrays;

public class SortedArrayMerger {

    public SortedArrayMerger() {
    }

    public int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < a.length && j < b.length) {
            result[k++] =
                    a[i] < b[j] ? a[i++] : b[j++];
        }

        while (i < a.length) {
            result[k++] = a[i++]; // Копируем остаток первого массива
        }

        while (j < b.length) {
            result[k++] = b[j++]; // Копируем остаток второго массива
        }

        return result; // Возвращаем объединённый массив
    }

    public static void main(String[] args) {
        SortedArrayMerger merger = new SortedArrayMerger();

        System.out.println(Arrays.toString(
                merger.mergeSortedArrays(
                        new int[]{1, 3, 5},
                        new int[]{2, 4, 6}
                )
        ));
        System.out.println(Arrays.toString(
                merger.mergeSortedArrays(
                        new int[]{1, 2, 2},
                        new int[]{2, 3}
                )
        ));
        System.out.println(Arrays.toString(
                merger.mergeSortedArrays(
                        new int[]{},
                        new int[]{4, 7}
                )
        ));
    }
}
