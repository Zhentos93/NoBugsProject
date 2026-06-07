package practice_15_1_lecture_Multithreading.CallableFuture;

import java.util.concurrent.*;

public class CallableFutureExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Поток 1 через Callable — запускаем первым
        Callable<Integer> t1 = () -> {
            System.out.println("Поток 1 (Callable): работа");
            Thread.sleep(2000);
            System.out.println("Поток 1 (Callable): завершение");
            return 42;
        };

        // Остальные потоки через Runnable
        Runnable t2 = () -> {
            System.out.println("Поток 2: работа");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            System.out.println("Поток 2: завершение");
        };

        Runnable t3 = () -> {
            System.out.println("Поток 3: работа");
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            System.out.println("Поток 3: завершение");
        };

        Runnable t4 = () -> {
            System.out.println("Поток 4: работа");
            try { Thread.sleep(1500); } catch (InterruptedException e) {}
            System.out.println("Поток 4: завершение");
        };

        Future<Integer> future1 = executor.submit(t1);

        // Далее запускаем Runnables
        executor.submit(t2);
        executor.submit(t3);
        executor.submit(t4);

        System.out.println("Main: выполняем другие действия, пока потоки работают...");

        // Получаем результат от Callable (main блокируется, если результат ещё не готов)
        int result = future1.get();
        System.out.println("Main: результат потока 1 = " + result);

        executor.shutdown();
        System.out.println("Main: завершение всех потоков через executor");
    }
}
