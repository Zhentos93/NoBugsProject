/*
Задача 1. Создайте TreeMap и добавьте 5 ключей (имена) и значений (баллы). Выведите отсортированные данные.
*/

package practice_11_homework_Collections.tree_map;

import java.util.Map;
import java.util.TreeMap;

public class Task1 {

    public void fiveNames() {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("Полина", 3);
        map.put("Саша", 2);
        map.put("Никита", 5);
        map.put("Женя", 5);
        map.put("Паша", 4);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " = " + value);
        }
    }
}