/*Задача: Напишите программу, которая принимает список чисел
и находит в нём самое большое число, используя Stream API.*/

package practice_15_3_homework_FunctionalInterfaces_Lambda_StreamAPI.aggregation_operations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FindMaxElement {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Integer maxElement = numbers
                .stream()
                .max(Comparator.naturalOrder())
                .orElse(0);

        System.out.println(maxElement);
    }
}
