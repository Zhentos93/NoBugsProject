/*Задача: Напишите программу, которая проверяет, есть ли хотя бы один элемент в списке, который
        удовлетворяет заданному условию (например, является чётным числом), используя Stream API.*/

package practice_14_3_homework_FunctionalInterfaces_Lambda_StreamAPI.aggregation_operations;

import java.util.Arrays;
import java.util.List;

public class CheckAnyMatch {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 3, 5, 7, 9, 11); // - вернёт false
//        List<Integer> numbers = Arrays.asList(1, 2, 3, 5, 7, 9, 11); // - вернёт true

        Boolean evenNumbers = numbers
                .stream()
                .anyMatch(n -> n % 2 == 0);

        System.out.println(evenNumbers);
    }
}
