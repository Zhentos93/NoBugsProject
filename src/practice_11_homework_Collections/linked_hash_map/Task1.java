/*
Задача 1. Создайте LinkedHashMap и добавьте в него 5 элементов. Выведите их в порядке добавления.
*/

package practice_11_homework_Collections.linked_hash_map;

import java.util.LinkedHashMap;
import java.util.Map;

public class Task1 {

    public void fiveElements() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("Никита", 12);
        map.put("Полина", 10);
        map.put("Саша", 11);
        map.put("Паша", 22);
        map.put("Женя", 21);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " = " + value);
        }
    }
}