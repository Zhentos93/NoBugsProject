/*Задача: Напишите программу, которая принимает список чисел
и преобразует его в новый список, где каждое число заменено на его квадрат, используя Stream API.*/

package practice_15_3_homework_FunctionalInterfaces_Lambda_StreamAPI.streamapi_baseoperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SquareNumbers {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(5, 10, 15, 20, 25, 30);

        List<Integer> numberSquareMapper = numbers
                .stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        System.out.println(numberSquareMapper);
    }
}
