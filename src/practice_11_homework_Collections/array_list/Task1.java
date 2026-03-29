/*
Задача 1. Создайте ArrayList из 5 чисел. Добавьте ещё одно число в конец. Выведите весь список.
*/

package practice_11_homework_Collections.array_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task1 {

    public void createFiveNumbers() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        list = new ArrayList<>(list);
        list.add(6);
        System.out.println(list);
    }
}