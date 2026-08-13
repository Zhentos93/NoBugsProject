package livecoding.algorithms.fourtyeight;

/*
 * Задача №48. Положительные числа
 *
 * Подсчитать количество положительных чисел.
 */

public class PositiveNumberCounter {

    public PositiveNumberCounter() {
    }

    public int countPositive(int[] nums) {
        int count = 0; // Количество положительных чисел

        for (int num : nums) { // Проверяем каждый элемент массива
            if (num > 0) {     // Ноль положительным не считается
                count++;
            }
        }

        return count; // Возвращаем количество найденных чисел
    }

    public static void main(String[] args) {
        PositiveNumberCounter counter =
                new PositiveNumberCounter();

        System.out.println(counter.countPositive(
                new int[]{-2, -1, 0, 1, 2, 3}
        ));
        System.out.println(counter.countPositive(
                new int[]{0, 0, 0}
        ));
        System.out.println(counter.countPositive(
                new int[]{1, 2, 3}
        ));
        System.out.println(counter.countPositive(
                new int[]{}
        ));
    }
}
