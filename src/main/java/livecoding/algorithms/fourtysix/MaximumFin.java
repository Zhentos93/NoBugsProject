package livecoding.algorithms.fourtysix;

/*
 * Задача №46. Максимум в массиве
 *
 * Найти максимальный элемент в массиве.
 */

// Ошибки
/*
 * Ошибки в задаче №46 — максимум в массиве:
 *
 * 1. Неправильно записан первый элемент:
 *    int max = [0];
 *
 *    Нужно:
 *    int max = arr[0];
 *
 * 2. Добавлена проверка из задачи №4, которой нет
 *    в примере решения №46.
 *
 * 3. Если такая проверка всё же используется, она должна
 *    находиться перед arr[0]. Иначе обращение произойдёт
 *    раньше проверки.
 */

public class MaximumFin {
    public MaximumFin() {
    }

    public int findMax(int[] arr) {
        int max = arr[0];

        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        MaximumFin maximumFin = new MaximumFin();

        System.out.println(maximumFin.findMax(new int[]{3, 8, 2, 5}));
        System.out.println(maximumFin.findMax(new int[]{-7, -2, -10}));
        System.out.println(maximumFin.findMax(new int[]{4}));
        System.out.println(maximumFin.findMax(new int[]{5, 5, 5}));
    }
}
