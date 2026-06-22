/*
Задача 1. Создайте LinkedList и добавьте в него 5 строк. Выведите все элементы списка.
*/

package practice_11_homework_Collections.linked_list;

import java.util.LinkedList;

public class Task1 {

    public void fiveStrings() {
        LinkedList<String> list = new LinkedList<>();
        list.add("Кошка");
        list.add("Собака");
        list.add("Попугай");
        list.add("Хомячок");
        list.add("Игуана");

        for (String pet : list) {
            System.out.println(pet);
        }
    }
}
