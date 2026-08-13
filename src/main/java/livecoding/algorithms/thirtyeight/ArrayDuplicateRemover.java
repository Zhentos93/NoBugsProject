package livecoding.algorithms.thirtyeight;

/*
 * Задача №38. Удаление дубликатов из массива
 *
 * Удалить все дубликаты из массива.
 */

import java.util.Arrays;

public class ArrayDuplicateRemover {

    public ArrayDuplicateRemover() {
    }

    public int[] removeDuplicates(int[] nums) {
        return Arrays.stream(nums)
                .distinct() // Оставляем первое появление каждого числа
                .toArray(); // Собираем элементы в новый массив
    }

    public static void main(String[] args) {
        ArrayDuplicateRemover remover =
                new ArrayDuplicateRemover();

        System.out.println(Arrays.toString(
                remover.removeDuplicates(
                        new int[]{1, 2, 2, 3, 1, 4}
                )
        ));
        System.out.println(Arrays.toString(
                remover.removeDuplicates(new int[]{5, 5, 5})
        ));
        System.out.println(Arrays.toString(
                remover.removeDuplicates(new int[]{})
        ));
        System.out.println(Arrays.toString(
                remover.removeDuplicates(
                        new int[]{-1, 0, -1, 0}
                )
        ));
    }
}
