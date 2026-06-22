/*Пример: без STREAM API. "Анна", "Борис", "Саша".
Задача: отфильтруй по принципу: все, что начинаются на букву "А", привести к большим буквам.
ОР: "АННА"  */

package practice_14_1_lecture_FunctionalInterface_Lambda_StreamAPI.streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task1_WithoutStreamAPI {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Анна", "Борис", "Владимир");
        List<String> filtered = new ArrayList<>();
        for (String name : names) {
            if (name.startsWith("А")) {
                filtered.add(name.toUpperCase());
            }
        }
        System.out.println(filtered); // [АННА]
    }
}
