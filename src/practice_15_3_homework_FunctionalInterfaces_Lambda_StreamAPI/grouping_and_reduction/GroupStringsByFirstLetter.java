/*Задача: Напишите программу, которая принимает список строк и группирует их по первой букве, используя Stream API.*/

package practice_15_3_homework_FunctionalInterfaces_Lambda_StreamAPI.grouping_and_reduction;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupStringsByFirstLetter {
    public static void main(String[] args) {
        List<String> countries = Arrays.asList("Бразилия", "Ангола", "Ватикан", "Грузия", "Австралия");

        // реализация через .collect(Collectors.groupingBy(s -> s.substring(0, 1)));
//        Map<String, List<String>> grouping = countries
//                .stream()
//                .collect(Collectors.groupingBy(s -> s.substring(0, 1)));

        // реализация через .collect(Collectors.groupingBy(s -> s.charAt(0)));
        Map<Character, List<String>> grouping = countries
                .stream()
                .collect(Collectors.groupingBy(s -> s.charAt(0)));

        System.out.println(grouping);
    }
}
