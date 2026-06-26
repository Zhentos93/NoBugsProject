// Напишите тесты для метода, который находит второе по величине число:

/*Тесты должны проверять:
Обычные массивы
Массив с одинаковыми числами
Один элемент в массиве (должно выбрасываться исключение)
*/

package practice_16_3_homework_WritingTests;

import java.util.Arrays;

public class FindSecondMaximumInArray {

    public int findSecondMax(int[] numbers) {
        return Arrays.stream(numbers).distinct().sorted().skip(numbers.length - 2).findFirst().orElseThrow();
    }
}
