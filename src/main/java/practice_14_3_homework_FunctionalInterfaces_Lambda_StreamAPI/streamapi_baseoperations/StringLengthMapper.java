/*Задача: Напишите программу, которая принимает список строк
и заменяет каждую строку на её длину, используя Stream API.*/

package practice_14_3_homework_FunctionalInterfaces_Lambda_StreamAPI.streamapi_baseoperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringLengthMapper {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Hello, world!", "Cat", "Dog", "Bear");

        List<Integer> filtered = strings
                .stream()
                .map(String::length)
                .collect(Collectors.toList());

        System.out.println(filtered);
    }
}
