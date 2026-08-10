package livecoding.algorithms.four;

/*
 * Задача №4. Поиск максимального числа
 *
 * Найти наибольшее число в массиве.
 */

public class ArrayMaximumFinder {

    public ArrayMaximumFinder() {
    }

    int max(int[] arr) {
        if (arr == null || arr.length == 0) { // Проверяем входные данные
            throw new IllegalArgumentException();
        }

        int max = arr[0]; // Начинаем с первого элемента

        for (int num : arr) { // По очереди проверяем все элементы
            if (num > max) {  // Нашли число больше текущего максимума
                max = num;
            }
        }

        return max; // Возвращаем максимум после полного прохода
    }

    public static void main(String[] args) {
        ArrayMaximumFinder finder = new ArrayMaximumFinder();

        System.out.println(finder.max(new int[]{3, 8, 2, 5}));
        System.out.println(finder.max(new int[]{-7, -2, -10}));
        System.out.println(finder.max(new int[]{4}));
    }
}
