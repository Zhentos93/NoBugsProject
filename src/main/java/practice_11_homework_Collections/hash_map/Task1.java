/*
Задача 1. Создайте HashMap<String, Integer>, добавьте 5 пар (имя – возраст) и выведите все записи.
*/

package practice_11_homework_Collections.hash_map;

import java.util.HashMap;
import java.util.Map;

public class Task1 {

    public void nameAndAge() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Полина", 18);
        map.put("Саша", 19);
        map.put("Никита", 20);
        map.put("Женя", 21);
        map.put("Паша", 22);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();   // Получаем ключ
            Integer value = entry.getValue(); // Получаем значение
            System.out.println(key + " = " + value);
        }
    }
}
