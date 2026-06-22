/*
Задача 2: Напишите программу, которая выводит все чётные числа из ArrayList.
*/

package practice_11_homework_Collections.array_list;

import java.util.ArrayList;

public class Task2 {

    public void evenNumbers() {

        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        for (Integer number : list) {
            if (number % 2 == 0) {
                System.out.println(number);
            }
        }
    }
}
