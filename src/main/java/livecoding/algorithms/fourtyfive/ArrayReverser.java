package livecoding.algorithms.fourtyfive;

/*
 * Задача №45. Разворот массива
 *
 * Перевернуть массив в обратном порядке.
 */

import java.util.Arrays;

public class ArrayReverser {

    public ArrayReverser() {
    }

    public void reverseArray(int[] nums) {
        int left = 0;                // Индекс первого элемента
        int right = nums.length - 1; // Индекс последнего элемента

        while (left < right) { // Указатели ещё не встретились
            int temp = nums[left];
            nums[left++] = nums[right];
            nums[right--] = temp;
        }
    }

    public static void main(String[] args) {
        ArrayReverser reverser = new ArrayReverser();

        int[] first = {1, 2, 3, 4, 5};
        reverser.reverseArray(first);
        System.out.println(Arrays.toString(first));

        int[] second = {1, 2, 3, 4};
        reverser.reverseArray(second);
        System.out.println(Arrays.toString(second));

        int[] oneElement = {7};
        reverser.reverseArray(oneElement);
        System.out.println(Arrays.toString(oneElement));

        int[] empty = {};
        reverser.reverseArray(empty);
        System.out.println(Arrays.toString(empty));
    }
}
