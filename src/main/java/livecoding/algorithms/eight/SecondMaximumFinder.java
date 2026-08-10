package livecoding.algorithms.eight;

/*
 * Задача №8. Поиск второго максимального элемента
 *
 * Найти второй по величине элемент в массиве.
 */

public class SecondMaximumFinder {

    public SecondMaximumFinder() {
    }

    int secondMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int n : arr) { // Проверяем каждый элемент массива
            if (n > max) {  // Найден новый максимальный элемент
                second = max;
                max = n;
            } else if (n > second && n != max) {
                second = n; // Обновляем второй отличный максимум
            }
        }

        return second; // Возвращаем второй максимум
    }

    public static void main(String[] args) {
        SecondMaximumFinder finder = new SecondMaximumFinder();

        System.out.println(finder.secondMax(new int[]{10, 5, 8, 10, 3}));
        System.out.println(finder.secondMax(new int[]{-5, -2, -9, -3}));
        System.out.println(finder.secondMax(new int[]{7}));
    }
}