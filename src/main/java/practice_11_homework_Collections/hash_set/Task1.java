/*
Задача 1. Создайте HashSet из 5 чисел и выведите его содержимое.
*/

package practice_11_homework_Collections.hash_set;

import java.util.HashSet;

public class Task1 {

    public void fiveNumbers() {
        HashSet<Integer> set = new HashSet<>();
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);

        for (Integer number : set) {
            System.out.println(number);
        }
    }
}
