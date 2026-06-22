package practice_15_1_lecture_Multithreading.Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterExample {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0); // атомарная переменная

        // Поток 1
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count.incrementAndGet(); // атомарный инкремент
            }
        });

        // Поток 2
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                count.incrementAndGet(); // атомарный инкремент
            }
        });

        // Запускаем потоки
        t1.start();
        t2.start();

        // Ждём завершения потоков
        t1.join();
        t2.join();

        // Выводим результаты
        System.out.println("Ожидаемое значение: 2000");
        System.out.println("Фактическое значение: " + count.get());
    }
}
