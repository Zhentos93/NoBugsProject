package livecoding.algorithms.fourtysix;

/*
 * Задача №46. Максимум в массиве
 *
 * Найти максимальный элемент в массиве.
 */

public class MaximumFinder {

    public MaximumFinder() {
    }

    public int findMax(int[] nums) {
        int max = nums[0]; // Начинаем с первого элемента

        for (int num : nums) { // Проверяем каждый элемент массива
            if (num > max) {   // Найдено большее значение
                max = num;
            }
        }

        return max; // Возвращаем максимум после полного прохода
    }

    public static void main(String[] args) {
        MaximumFinder finder = new MaximumFinder();

        System.out.println(
                finder.findMax(new int[]{3, 8, 2, 5})
        );
        System.out.println(
                finder.findMax(new int[]{-7, -2, -10})
        );
        System.out.println(
                finder.findMax(new int[]{4})
        );
        System.out.println(
                finder.findMax(new int[]{5, 5, 5})
        );
    }
}
