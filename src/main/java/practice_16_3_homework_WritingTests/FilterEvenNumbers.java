// Напишите тесты на метод, который фильтрует список чисел (оставляет только чётные)

/*
Тесты:
Обычный список ([1, 2, 3, 4, 5, 6] → [2, 4, 6])
Список без чётных чисел
Пустой список
*/

package practice_16_3_homework_WritingTests;

import java.util.List;
import java.util.stream.Collectors;

public class FilterEvenNumbers {
    public List<Integer> filterEvenNumbers(List<Integer> numbers) {
        return numbers
                .stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
    }
}
