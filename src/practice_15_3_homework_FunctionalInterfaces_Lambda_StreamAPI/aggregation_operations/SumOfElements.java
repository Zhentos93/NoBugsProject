/*Задача: Напишите программу, которая принимает список чисел и вычисляет их сумму, используя Stream API.*/

package practice_15_3_homework_FunctionalInterfaces_Lambda_StreamAPI.aggregation_operations;

import java.util.Arrays;
import java.util.List;

public class SumOfElements {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer sumOfElements = numbers
                .stream()
                .mapToInt(n -> n)
                .sum();

        System.out.println(sumOfElements);
    }
}
