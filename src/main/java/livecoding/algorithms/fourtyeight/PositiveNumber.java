package livecoding.algorithms.fourtyeight;

/*
 * Задача №48. Положительные числа
 *
 * Подсчитать количество положительных чисел.
 */

// Ошибок при решении нет

public class PositiveNumber {
    public PositiveNumber() {
    }

    public int countPositive(int[] nums) {
        int count = 0;

        for (int num : nums) {
            if (num > 0) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        PositiveNumber positiveNumber = new PositiveNumber();

        System.out.println(positiveNumber.countPositive(new int[]{-2, -1, 0, 1, 2, 3}));
        System.out.println(positiveNumber.countPositive(new int[]{0, 0, 0}));
        System.out.println(positiveNumber.countPositive(new int[]{1, 2, 3}));
        System.out.println(positiveNumber.countPositive(new int[]{}));
    }
}
