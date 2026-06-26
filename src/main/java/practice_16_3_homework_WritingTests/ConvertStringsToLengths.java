// Напишите тесты на метод, который преобразует список строк в список их длин

/*
Тесты:
    [ "Java", "C++", "Go" ] → [ 4, 3, 2 ]
    Пустой список
*/

package practice_16_3_homework_WritingTests;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertStringsToLengths {

    public List<Integer> mapToLengths(List<String> words) {
        return words.stream().map(String::length).collect(Collectors.toList());
    }

}
