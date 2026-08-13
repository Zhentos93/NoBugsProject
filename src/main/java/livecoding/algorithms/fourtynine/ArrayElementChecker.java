package livecoding.algorithms.fourtynine;

/*
 * Задача №49. Содержит элемент
 *
 * Проверить, есть ли элемент в массиве.
 */

public class ArrayElementChecker {

    public ArrayElementChecker() {
    }

    public boolean contains(int[] nums, int target) {
        for (int num : nums) { // Проверяем элементы по очереди
            if (num == target) { // Найдено искомое значение
                return true;
            }
        }

        return false; // Совпадение не найдено
    }

    public static void main(String[] args) {
        ArrayElementChecker checker = new ArrayElementChecker();

        System.out.println(checker.contains(
                new int[]{1, 2, 3, 4}, 3
        ));
        System.out.println(checker.contains(
                new int[]{1, 2, 3, 4}, 7
        ));
        System.out.println(checker.contains(
                new int[]{5, 5, 5}, 5
        ));
        System.out.println(checker.contains(
                new int[]{}, 1
        ));
    }
}
