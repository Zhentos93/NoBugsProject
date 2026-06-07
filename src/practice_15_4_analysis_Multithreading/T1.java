package practice_15_4_analysis_Multithreading;

public class T1 {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> System.out.println(Thread.currentThread().getName() + ": child"));
        t.run();
        t.start();
        t.join();
        System.out.println(Thread.currentThread().getName() + ": main");
    }
}

/*T1 (Thread, run(), start(), join())
Главное внимание:
        1. t.run() не создаёт новый поток, выполняется в текущем (main).
        2. t.start() — запускает новый поток.
        3. join() блокирует main до завершения потока t.
        4. Thread.currentThread() всегда показывает тот поток, который реально выполняет код.

Помни: run() ≠ start(), join нужен только если main/другой поток должен дождаться завершения.*/
