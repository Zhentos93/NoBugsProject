package livecoding.algorithms.fiftyfive;

/*
 * Задача №55. Минимум в массиве
 *
 * Найти минимальное число в массиве.
 */

// Ошибок при решении нет

public class MinimumFin {
    public MinimumFin() {
    }

    public int findMin(int[] nums) {
        int min = nums[0];

        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }

        return min;
    }

    public static void main(String[] args) {
        MinimumFin minimumFin = new MinimumFin();

        System.out.println(minimumFin.findMin(new int[]{3, 8, 2, 5}));
        System.out.println(minimumFin.findMin(new int[]{-7, -2, -10}));
        System.out.println(minimumFin.findMin(new int[]{4}));
        System.out.println(minimumFin.findMin(new int[]{5, 5, 5}));
        System.out.println(minimumFin.findMin(new int[]{0, 3, -1, 2}));
    }
}
