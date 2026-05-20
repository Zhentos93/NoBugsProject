/*Задача: Напишите программу, которая принимает список чисел и находит в нем наименьшее число, используя Stream API.*/

package practice_15_3_homework_FunctionalInterfaces_Lambda_StreamAPI.aggregation_operations;

import java.util.Arrays;
import java.util.List;

public class FindMinElement {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // через .min(Integer::compare)
        Integer minElement = numbers
                .stream()
                .min(Integer::compare)
                .orElse(0);

        // через .max(Comparator.reverseOrder())
//        Integer minElement = numbers
//                .stream()
//                .max(Comparator.reverseOrder())
//                .orElse(0);

        System.out.println(minElement);
    }
}
