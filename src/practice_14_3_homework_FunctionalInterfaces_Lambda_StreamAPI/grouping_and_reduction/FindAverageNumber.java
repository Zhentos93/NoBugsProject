/*Задача: Напишите программу, которая принимает список чисел и находит их среднее значение, используя Stream API.*/

package practice_14_3_homework_FunctionalInterfaces_Lambda_StreamAPI.grouping_and_reduction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindAverageNumber {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Double average = numbers
                .stream()
                .collect(Collectors.averagingInt(n -> n));

        System.out.println(average);
    }
}
