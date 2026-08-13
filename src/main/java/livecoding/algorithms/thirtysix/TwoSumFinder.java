package livecoding.algorithms.thirtysix;

/*
 * Задача №36. Сумма двух чисел
 *
 * Найти индексы двух чисел в массиве,
 * сумма которых равна target.
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumFinder {

    public TwoSumFinder() {
    }

    int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // Вычисляем недостающее число

            if (map.containsKey(complement)) { // Ищем его среди пройденных
                return new int[]{map.get(complement), i};
            }

            map.put(nums[i], i); // Запоминаем число и его индекс
        }

        return new int[0]; // Подходящая пара не найдена
    }

    public static void main(String[] args) {
        TwoSumFinder finder = new TwoSumFinder();

        System.out.println(Arrays.toString(
                finder.twoSum(new int[]{2, 7, 11, 15}, 9)
        ));
        System.out.println(Arrays.toString(
                finder.twoSum(new int[]{3, 2, 4}, 6)
        ));
        System.out.println(Arrays.toString(
                finder.twoSum(new int[]{3, 3}, 6)
        ));
        System.out.println(Arrays.toString(
                finder.twoSum(new int[]{1, 2, 3}, 10)
        ));
    }
}
