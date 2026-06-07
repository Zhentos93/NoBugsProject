/*Задача: Напишите программу, которая принимает список строк и
удаляет из него все строки длиной 5 символов и менее, используя Stream API.*/

package practice_14_3_homework_FunctionalInterfaces_Lambda_StreamAPI.streamapi_baseoperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringLengthFilter {
    public static void main(String[] args) {
        List<String> listOfStrings = Arrays.asList("Hello, world!", "Hero", "Superman");

        List<String> filtered = listOfStrings
                .stream()
                .filter(s -> s.length() > 5)
                .collect(Collectors.toList());

        System.out.println(filtered);
    }
}
