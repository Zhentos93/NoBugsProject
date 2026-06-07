package practice_15_4_analysis_Multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class T3 {
    // Потокобезопасный счётчик
    static AtomicInteger x = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        // Создаём два потока, каждый делает 1_000_000 инкрементов
        Thread a = new Thread(() -> { for (int i = 0; i < 1_000_000; i++) x.incrementAndGet(); });
        Thread b = new Thread(() -> { for (int i = 0; i < 1_000_000; i++) x.incrementAndGet(); });

        // Запускаем потоки
        a.start(); b.start();

        // Ждём, пока оба потока закончат работу
        a.join(); b.join();

        // Выводим итоговое значение (должно быть 2_000_000)
        System.out.println("Итоговое значение x: " + x.get());
    }
}

/*T3 (AtomicInteger, потокобезопасные инкременты)
Главное внимание:
        1. AtomicInteger.incrementAndGet() — атомарная операция, гарантирует, что параллельные инкременты не теряются, даже без synchronized.
        2. join() нужен, чтобы main дождался завершения потоков перед выводом значения.
        3. Сравни с предыдущей задачей с volatile int x — там гонка данных, здесь AtomicInteger решает проблему.

Помни: volatile гарантирует видимость, но не атомарность; для счётчиков в многопоточности лучше AtomicInteger или synchronized.*/
