package livecoding.algorithms.fiftyfive;

/*
 * Задача №55. Минимум в массиве
 *
 * Найти минимальное число в массиве.
 */

public class MinimumFinder {

    public MinimumFinder() {
    }

    public int findMin(int[] nums) {
        int min = nums[0]; // Начинаем с первого элемента

        for (int num : nums) { // Проверяем каждый элемент массива
            if (num < min) {   // Найдено меньшее значение
                min = num;
            }
        }

        return min; // Возвращаем минимум после полного прохода
    }

    public static void main(String[] args) {
        MinimumFinder finder = new MinimumFinder();

        System.out.println(
                finder.findMin(new int[]{3, 8, 2, 5})
        );
        System.out.println(
                finder.findMin(new int[]{-7, -2, -10})
        );
        System.out.println(
                finder.findMin(new int[]{4})
        );
        System.out.println(
                finder.findMin(new int[]{5, 5, 5})
        );
        System.out.println(
                finder.findMin(new int[]{0, 3, -1, 2})
        );
    }
}
