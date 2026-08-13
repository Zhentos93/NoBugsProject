package livecoding.algorithms.fiftyone;

/*
 * Задача №51. Сравнить массивы
 *
 * Сравнить два массива
 * по содержимому и порядку элементов.
 */

// Ошибки
/*
 * Ошибки в задаче №51 — сравнить массивы:
 *
 * 1. Использовался метод массива equals():
 *    a.equals(b)
 *
 *    Для массивов он не сравнивает элементы по позициям.
 *    Нужно:
 *    Arrays.equals(a, b)
 *
 * 2. Для исправленного варианта требуется импорт:
 *    import java.util.Arrays;
 *
 * Вызов arraysEqual() в main и передача его результата
 * в println() написаны правильно.
 */

import java.util.Arrays;

public class ArrayEquality {
    public ArrayEquality() {
    }

    public boolean arraysEqual(int[] a, int[] b) {
        return Arrays.equals(a, b);
    }

    public static void main(String[] args) {
        ArrayEquality arrayEquality = new ArrayEquality();

        System.out.println(arrayEquality.arraysEqual(new int[]{1, 2, 3}, new int[]{1, 2, 3}));
        System.out.println(arrayEquality.arraysEqual(new int[]{1, 2, 3}, new int[]{3, 2, 1}));
        System.out.println(arrayEquality.arraysEqual(new int[]{1, 2}, new int[]{1, 2, 3}));
        System.out.println(arrayEquality.arraysEqual(new int[]{}, new int[]{}));
        System.out.println(arrayEquality.arraysEqual(null, null));
    }
}
