package livecoding.algorithms.fiftytwo;

/*
 * Задача №52. Чётные и нечётные
 *
 * Подсчитать чётные и нечётные числа.
 */

import java.util.Arrays;

public class EvenOddCounter {

    public EvenOddCounter() {
    }

    public int[] countEvenOdd(int[] nums) {
        int even = 0;
        int odd = 0;

        for (int n : nums) { // Проверяем каждый элемент массива
            if (n % 2 == 0) {
                even++; // Увеличиваем количество чётных
            } else {
                odd++;  // Увеличиваем количество нечётных
            }
        }

        return new int[]{even, odd}; // Сначала чётные, затем нечётные
    }

    public static void main(String[] args) {
        EvenOddCounter counter = new EvenOddCounter();

        System.out.println(Arrays.toString(
                counter.countEvenOdd(
                        new int[]{1, 2, 3, 4, 5, 6}
                )
        ));
        System.out.println(Arrays.toString(
                counter.countEvenOdd(
                        new int[]{-3, -2, -1, 0, 2}
                )
        ));
        System.out.println(Arrays.toString(
                counter.countEvenOdd(
                        new int[]{2, 4, 6}
                )
        ));
        System.out.println(Arrays.toString(
                counter.countEvenOdd(
                        new int[]{}
                )
        ));
    }
}
