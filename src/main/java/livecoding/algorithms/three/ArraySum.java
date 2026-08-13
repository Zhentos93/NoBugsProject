package livecoding.algorithms.three;

/*
 * Задача №3. Сумма чисел массива
 *
 * Посчитать сумму всех чисел в массиве.
 */

// Ошибки
/*
 * Ошибки в задаче №3 — сумма чисел массива:
 *
 * 1. Изменялся текущий элемент массива:
 *    num += sum;
 *
 *    Нужно изменять накопитель:
 *    sum += num;
 *
 * 2. Массив создавался с неправильным синтаксисом:
 *    new int[1, 2, 3, 4]
 *
 *    Правильно:
 *    new int[]{1, 2, 3, 4}
 */

public class ArraySum {

    public ArraySum() {
    }

    public int sum(int[] numbers) {
        int sum = 0;

        for (int num : numbers) {
            sum += num;
        }

        return sum;
    }

    public static void main(String[] args) {
        ArraySum arraySum = new ArraySum();

        System.out.println(arraySum.sum(new int[]{1, 2, 3, 4}));
        System.out.println(arraySum.sum(new int[]{-5, 2, 3}));
        System.out.println(arraySum.sum(new int[]{}));
    }
}
