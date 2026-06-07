/*Пример: c STREAM API. "Анна", "Борис", "Саша".
Задача: отфильтруй по принципу: все, что начинаются на букву "А", привести к большим буквам.
ОР: "АННА"  */

package practice_14_1_lecture_FunctionalInterface_Lambda_StreamAPI.streamapi;

import java.util.List;
import java.util.stream.Collectors;

public class Task2_WithStreamAPI {
    public static void main(String[] args) {
        List<String> names = List.of("Анна", "Иван", "Мария");

        List<String> filtered = names.stream()
                .filter(name -> name.startsWith("А"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(filtered); // [АННА]
    }
}
