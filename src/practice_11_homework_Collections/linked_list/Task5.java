/*
Задача 5. Используйте ListIterator для прохода по LinkedList в обоих направлениях.
*/

package practice_11_homework_Collections.linked_list;

import java.util.LinkedList;
import java.util.ListIterator;

public class Task5 {

    public void twoDirections() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Первый");
        list.add("Второй");
        list.add("Третий");
        list.add("Четвертый");
        list.add("Пятый");

        ListIterator<String> it = list.listIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println(" ");
        while (it.hasPrevious()) {
            System.out.println(it.previous());
        }
    }
}
