// Напишите тесты на метод, который находит среднее значение в массиве чисел

/*
Тесты:
        [1, 2, 3, 4, 5] → 3.0
        [10] → 10.0
        Пустой массив (должно выбрасываться исключение)
*/

package practice_16_3_homework_WritingTests;

import java.util.Arrays;

public class CalculateAverageOfArray {

    public double findAverage(int[] numbers) {
        return Arrays.stream(numbers).average().orElseThrow();
    }
}
