/*Задача: Напишите программу, которая принимает список строк
и находит первую строку, начинающуюся на букву "Б", используя Stream API.*/

package practice_15_3_homework_FunctionalInterfaces_Lambda_StreamAPI.aggregation_operations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class FindFirstStartingWithB {
    public static void main(String[] args) {
        List<String> countries = Arrays.asList("Ангола", "Бразилия", "Ватикан");

        Optional<String> firstElement = countries
                .stream()
                .filter(s -> s.startsWith("Б"))
                .findFirst();

        System.out.println(firstElement);
    }
}
