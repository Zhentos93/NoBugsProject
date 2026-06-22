/*Задача: Напишите программу, которая принимает список чисел и
группирует их на чётные и нечётные, используя Stream API.*/

package practice_14_3_homework_FunctionalInterfaces_Lambda_StreamAPI.grouping_and_reduction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupNumbersByParity {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Map<Boolean, List<Integer>> evenNumbers = numbers
                .stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0));

        System.out.println(evenNumbers);
    }
}
