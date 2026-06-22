/*
Задача 1. Создайте LinkedHashSet и добавьте в него 5 строк. Проверьте порядок элементов при выводе.
*/

package practice_11_homework_Collections.linked_hash_set;

import java.util.LinkedHashSet;

public class Task1 {

    public void orderInNumbers() {
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("Да");
        set.add("Здравствует");
        set.add("Мир!");

        for (String string : set) {
            System.out.println(string);
        }
    }
}
