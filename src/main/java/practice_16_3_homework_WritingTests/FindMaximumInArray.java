// Напишите тесты для метода, который находит максимальное число в массиве:

/*
Тесты должны проверять:
Обычный массив ([3, 5, 7, 2])
Один элемент в массиве
Отрицательные числа
Пустой массив (должно выбрасываться исключение)
*/

package practice_16_3_homework_WritingTests;

import java.util.Arrays;

public class FindMaximumInArray {

    public int findMax(int[] numbers) {
        return Arrays.stream(numbers).max().orElseThrow();
    }
}
