package practice_15_4_analysis_Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class T7 {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<String> f1 = pool.submit(() -> {
            Thread.sleep(100);
            return "A";
        });

        Future<String> f2 = pool.submit(() -> "B");

        System.out.println(f1.get() + f2.get());
        pool.shutdown();
    }
}

/*T7 — ExecutorService + Future
Главное внимание:
    1. Future.get() блокирует main поток, пока результат задачи не готов. Main ждёт f1, затем f2, и только потом объединяет их результат.
    2. Порядок выполнения задач в пуле не гарантирован, но get() обеспечивает детерминированный итоговый результат ("A" + "B").
    3. Пул из 2 потоков позволяет выполнять задачи параллельно, но main не продолжит печать результата, пока обе задачи не завершатся.

Обрати внимание: блокировка main потока через get() — это нормальная практика, когда нужно дождаться результата асинхронной задачи.*/
