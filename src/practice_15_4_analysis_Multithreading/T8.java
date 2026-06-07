package practice_15_4_analysis_Multithreading;

public class T8 {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // busy work
            }
            System.out.println("stopped");
        });

        t.start();
        Thread.sleep(50);
        t.interrupt();
        t.join();
        System.out.println("main");
    }
}

/*Потоки в Java — main vs. отдельные потоки
Главное внимание:
    1. Всё, что находится в main(), выполняется в главном потоке main.
    2. Любой код внутри Runnable, Callable или Thread.run() выполняется в отдельном потоке, параллельно main.
    3. start() запускает поток параллельно, join() позволяет main дождаться его завершения — порядок запуска потоков
    не гарантирует момент начала выполнения, поэтому важно использовать синхронизацию или join, если нужен порядок.

Обрати внимание: визуально код может быть рядом в main, но фактически выполняется в другом потоке.*/
