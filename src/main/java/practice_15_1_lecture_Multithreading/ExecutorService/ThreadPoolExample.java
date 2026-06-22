package practice_15_1_lecture_Multithreading.ExecutorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample {
    public static void main(String[] args) {
        // Создаём пул из 3 потоков
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Создаём и отправляем 5 задач
        for (int i = 1; i <= 5; i++) {
            final int taskNumber = i;
            executor.execute(() -> {
                System.out.println("Выполняется задача " + taskNumber +
                        " в " + Thread.currentThread().getName());
            });
        }

        // Останавливаем executor
        executor.shutdown();
    }
}

// в рамках одного пула создаётся 3 потока, которые между собой распределяют 5 задач между собой
