/*Задача: Напишите программу, которая принимает список чисел и отбирает только те, которые делятся на 5 без остатка,
используя Stream API.*/

package practice_14_3_homework_FunctionalInterfaces_Lambda_StreamAPI.streamapi_baseoperations;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MultiplesOfFiveFilter {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(4, 5, 6, 7, 8, 9, 10);

        List<Integer> filtered = numbers
                .stream()
                .filter(x -> x % 5 == 0)
                .collect(Collectors.toList());

        System.out.println(filtered);
    }
}
