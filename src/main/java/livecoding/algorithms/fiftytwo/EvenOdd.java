package livecoding.algorithms.fiftytwo;

/*
 * Задача №52. Чётные и нечётные
 *
 * Подсчитать чётные и нечётные числа.
 */

// Ошибки
/*
 * Ошибки в задаче №52 — чётные и нечётные:
 *
 * 1. Для начала решения потребовалась подсказка
 *    по структуре алгоритма.
 *
 * 2. Результирующий массив выводился напрямую
 *    без Arrays.toString(), поэтому вывод был нечитаемым.
 *
 *    Нужно:
 *    System.out.println(Arrays.toString(
 *            evenOdd.countEvenOdd(...)
 *    ));
 */

import java.util.Arrays;

public class EvenOdd {
    public EvenOdd() {
    }

    public int[] countEvenOdd(int[] nums) {
        int even = 0;
        int odd = 0;

        for (int num : nums) {
            if (num % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        return new int[]{even, odd};
    }

    public static void main(String[] args) {
        EvenOdd evenOdd = new EvenOdd();

        System.out.println(Arrays.toString(evenOdd.countEvenOdd(new int[]{1, 2, 3, 4, 5, 6})));
        System.out.println(Arrays.toString(evenOdd.countEvenOdd(new int[]{-3, -2, -1, 0, 2})));
        System.out.println(Arrays.toString(evenOdd.countEvenOdd(new int[]{2, 4, 6})));
        System.out.println(Arrays.toString(evenOdd.countEvenOdd(new int[]{})));
    }
}
