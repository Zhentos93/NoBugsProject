package practice_17_2_practice_Trubleshuting.mistake;

import java.util.ArrayList;
import java.util.List;

/**
 * Программа предназначена для хранения и обработки списка строк.
 * Она должна добавлять строки в список, если они еще не присутствуют в нем,
 * и выводить финальный размер списка.
 * Однако в конечном списке появляются дубликаты.
 */
public class StringListHandler {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String[] inputs = {"apple", "banana", "apple", "Orange", "Banana", "Grape", "Orange"};

        for (String input : inputs) {
            if (!list.contains(input.toLowerCase())) {
                list.add(input.toLowerCase());
            }
        }

        System.out.println("Размер списка: " + list.size()); // Ожидаемый размер: 4
        System.out.println("Содержимое списка: " + list);
    }
}

/* было
for (String input : inputs) {
     if (!list.contains(input)) {
         list.add(input);
     }
}
*/
