package practice_15_1_lecture_Multithreading.Atomic;

class Counter {
    public int count = 0;

    public void increment() {
        count++; // НЕ атомарно
    }
}

public class AtomicityProblem {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // Создаем два потока, которые увеличивают count по 1000 раз
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Ожидаемое значение: 2000");
        System.out.println("Реальное значение: " + counter.count);
    }
}
