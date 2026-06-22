/*
Задача 1. Создайте TreeSet из 5 чисел и выведите его. Обратите внимание на порядок.
*/

package practice_11_homework_Collections.tree_set;

import java.util.TreeSet;

public class Task1 {

    public void automaticSortingInTreeSet() {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(45);
        set.add(54);
        set.add(11);
        set.add(1590);
        set.add(1);

        for (Integer number : set) {
            System.out.println(number);
        }
    }
}
