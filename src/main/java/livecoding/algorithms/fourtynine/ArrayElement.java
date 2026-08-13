package livecoding.algorithms.fourtynine;

/*
 * Задача №49. Содержит элемент
 *
 * Проверить, есть ли элемент в массиве.
 */

// Ошибки
/*
 * Ошибки в задаче №49 — содержит элемент:
 *
 * 1. У массива int[] вызывался метод contains():
 *    nums.contains(target)
 *
 *    У массивов такого метода нет. Нужно сравнивать
 *    текущий элемент цикла с target:
 *    if (arr == target)
 *
 * 2. При отсутствии совпадения сразу возвращался false.
 *    Установить отсутствие элемента можно только после
 *    проверки всего массива.
 *
 * 3. Результаты были перепутаны:
 *    при совпадении нужно сразу вернуть true,
 *    после полного обхода — false.
 *
 * 4. Переменная arr из for-each не использовалась.
 */

public class ArrayElement {
    public ArrayElement() {
    }

    public boolean contains(int[] nums, int target) {
        for (int arr : nums) {
            if (arr == target) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        ArrayElement arrayElement = new ArrayElement();

        System.out.println(arrayElement.contains(new int[]{1, 2, 3, 4}, 3));
        System.out.println(arrayElement.contains(new int[]{1, 2, 3, 4}, 7));
        System.out.println(arrayElement.contains(new int[]{5, 5, 5}, 5));
        System.out.println(arrayElement.contains(new int[]{}, 1));
    }
}
