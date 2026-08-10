package livecoding.algorithms.three;

/*
 * Задача №3. Сумма чисел массива
 *
 * Посчитать сумму всех чисел в массиве.
 */

public class ArraySumCalculator {

    public ArraySumCalculator() {
    }

    int sum(int[] arr) {
        int total = 0; // Начальное значение суммы

        for (int num : arr) {
            total += num;
        }

        return total;
    }

    public static void main(String[] args) {
        ArraySumCalculator calculator = new ArraySumCalculator();

        System.out.println(calculator.sum(new int[]{1, 2, 3, 4}));
        System.out.println(calculator.sum(new int[]{-5, 2, 3}));
        System.out.println(calculator.sum(new int[]{}));
    }
}
