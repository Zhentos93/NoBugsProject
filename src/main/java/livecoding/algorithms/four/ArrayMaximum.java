package livecoding.algorithms.four;

/*
 * Задача №4. Поиск максимального числа
 *
 * Найти наибольшее число в массиве.
 */

// Ошибки
/*
 * Ошибки в задаче №4 — поиск максимального числа:
 *
 * 1. Не проверялись null и пустой массив.
 *
 *    Нужно:
 *    if (numbers == null || numbers.length == 0) {
 *        throw new IllegalArgumentException();
 *    }
 *
 * 2. Максимум был изначально равен 0:
 *    int max = 0;
 *
 *    Это даёт неправильный результат для массива,
 *    состоящего только из отрицательных чисел.
 *
 *    Правильно:
 *    int max = numbers[0];
 */

public class ArrayMaximum {

    public ArrayMaximum() {
    }

    public int findMax(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException();
        }

        int max = numbers[0];

        for (int arr : numbers) {
            if (arr > max) {
                max = arr;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        ArrayMaximum arrayMaximum = new ArrayMaximum();

        System.out.println(arrayMaximum.findMax(new int[]{3, 8, 2, 5}));
        System.out.println(arrayMaximum.findMax(new int[]{-1, -2, -3, -4}));
        System.out.println(arrayMaximum.findMax(new int[]{4}));
    }
}
