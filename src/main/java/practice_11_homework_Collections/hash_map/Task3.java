/*
Задача 3. Реализуйте метод, который печатает из HashMap всех пользователей младше 18 лет.
*/

package practice_11_homework_Collections.hash_map;

import java.util.HashMap;
import java.util.Map;

public class Task3 {

    public void printUnderage() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Полина", 10);
        map.put("Саша", 11);
        map.put("Никита", 12);
        map.put("Женя", 21);
        map.put("Паша", 22);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (entry.getValue() < 18) {
                System.out.println(key + " = " + value);
            }
        }
    }
}
