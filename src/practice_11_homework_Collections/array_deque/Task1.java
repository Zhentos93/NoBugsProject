/*
Задача 1. Создайте ArrayDeque, добавьте 5 элементов и выведите их.
*/

package practice_11_homework_Collections.array_deque;

import java.util.ArrayDeque;

public class Task1 {

    public void fiveElements() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(6);
        deque.addLast(66);
        deque.addLast(63);
        deque.addLast(36);
        deque.addLast(363);

        for (Integer number : deque) {
            System.out.println(number);
        }
    }

}
