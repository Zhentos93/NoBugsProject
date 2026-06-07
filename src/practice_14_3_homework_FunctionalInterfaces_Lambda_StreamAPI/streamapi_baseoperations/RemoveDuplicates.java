/*Задача: Напишите программу, которая принимает список элементов
и удаляет из него все дубликаты, используя Stream API.*/

package practice_14_3_homework_FunctionalInterfaces_Lambda_StreamAPI.streamapi_baseoperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveDuplicates {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 4, 1, 2, 6, 8, 9, 8, 10);

        List<Integer> filtered = numbers
                .stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(filtered);
    }
}
