/*Задача: разработать многопоточное приложение, которое использует AtomicInteger для количества выполненных операций
* по всем потокам. Потоки выполняют операцию увеличения счётчика в цикле и в конце выводит общее значение операции*/

package practice_15_2_practice_Multithreading.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    // считает количество операций по всем потокам
    public static final AtomicInteger count = new AtomicInteger(0);
}
