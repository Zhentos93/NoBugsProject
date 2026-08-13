package livecoding.algorithms.twentyseven;

/*
 * Задача №27. Вывод элементов очереди
 *
 * Использовать Queue для вывода элементов
 * в порядке добавления.
 */

import java.util.LinkedList;
import java.util.Queue;

public class QueuePrinter {

    public QueuePrinter() {
    }

    void printQueue(Queue<String> queue) {
        while (!queue.isEmpty()) { // Выполняем цикл, пока есть элементы
            System.out.println(queue.poll());
        }
    }

    public static void main(String[] args) {
        QueuePrinter printer = new QueuePrinter();

        Queue<String> queue = new LinkedList<>();
        queue.offer("first");
        queue.offer("second");
        queue.offer("third");

        printer.printQueue(queue);
        System.out.println(queue);

        Queue<String> emptyQueue = new LinkedList<>();
        printer.printQueue(emptyQueue);
    }
}
