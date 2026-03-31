/*
Задача 2. Добавьте в HashSet 10 чисел. Проверьте, содержит ли он заданное число.
*/

package practice_11_homework_Collections.hash_set;

import java.util.HashSet;

public class Task2 {

    public void checkingForNumber(Integer number) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(5);
        set.add(6);
        set.add(7);
        set.add(8);
        set.add(9);
        set.add(10);

        if (set.contains(number)) {
            System.out.println("Такое число есть");
        } else {
            System.out.println("Sorry, bro");
        }
    }
}
