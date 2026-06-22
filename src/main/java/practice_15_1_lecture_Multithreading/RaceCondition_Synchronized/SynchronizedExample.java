package practice_15_1_lecture_Multithreading.RaceCondition_Synchronized;

class Counter1 {
    private int count = 0;

    public synchronized void increment() {
        count++; // потенциально небезопасная операция в многопоточности
    }

    public synchronized int getCount() {
        return count;
    }
}

public class SynchronizedExample {
    public static void main(String[] args) throws InterruptedException {
        Counter1 counter1 = new Counter1();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter1.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter1.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Итоговое значение: " + counter1.getCount()); // ожидаем 2000, но может быть меньше
    }
}


