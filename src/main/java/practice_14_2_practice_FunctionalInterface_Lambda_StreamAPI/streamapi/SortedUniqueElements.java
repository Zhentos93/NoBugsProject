package practice_14_2_practice_FunctionalInterface_Lambda_StreamAPI.streamapi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortedUniqueElements {
    // изначально дан список с дублирующими значениями
    // нужно вывести все уникальные значения в отсортированном порядке

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 2, 3, 1, 4, 2, 5);

        List<Integer> uniqueSorted = numbers.stream()
                .distinct()
                .peek(n-> System.out.println("distinct: " + n))
                .sorted()
                .peek(n-> System.out.println("sorted: " + n))
                .collect(Collectors.toList());

        System.out.println(uniqueSorted);
    }
}
