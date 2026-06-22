/*Задача: получить в консоли "Значение count: 0"*/

package practice_15_2_practice_Multithreading.synchronizedkeyword;

public class SafeCounter {
    // методы по увеличению и уменьшению значения
    // задача реализовать решение в многопоточной среде
    private int count = 0;

    public synchronized void increment() {
        this.count++;
    }

    public synchronized void decrement() {
        this.count--;
    }

    public synchronized int getCount() {
        return this.count;
    }
}