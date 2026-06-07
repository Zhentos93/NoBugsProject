/*Задача: необходимо вычислить квадрат числа с помощью Callable и после этого дождаться исполнения вычисления и
распечатать его значение*/

package practice_15_2_practice_Multithreading.callablefuture;

import java.util.Random;
import java.util.concurrent.*;

public class CallableFutureExample {
    // Сложные расчеты ДНК
    // Наша задача - дождаться вычисления и получить результат

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<Integer> dnaResult = () -> {
            System.out.println("Сложное вычисление DNA");
            Thread.sleep(10000);
            int randomDnaResult = new Random().nextInt();
            return randomDnaResult;
        };

        Future<Integer> future = executorService.submit(dnaResult);

        System.out.println("Результат DNA: " + future.get());

        executorService.shutdown();
    }
}
