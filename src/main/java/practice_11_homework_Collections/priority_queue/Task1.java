/*
Задача 1. Создайте PriorityQueue и добавьте 5 чисел. Выведите их в порядке удаления.
*/

package practice_11_homework_Collections.priority_queue;

import java.util.PriorityQueue;

public class Task1 {

    public void fiveNumbers() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(5);
        queue.add(2);
        queue.add(3);
        queue.add(9);
        queue.add(1);

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }
}
