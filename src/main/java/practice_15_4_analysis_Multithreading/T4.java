package practice_15_4_analysis_Multithreading;

public class T4 {
    static final Object A = new Object();
    static final Object B = new Object();

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            synchronized (A) {
                sleep(50);
                synchronized (B) {
                    System.out.println("t1");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (B) {
                sleep(50);
                synchronized (A) {
                    System.out.println("t2");
                }
            }
        });

        t1.start(); t2.start();
        t1.join(); t2.join();
        System.out.println("main");
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}

/*T4 — Deadlock
Главное внимание:
    1. Потоки t1 и t2 захватывают ресурсы в разном порядке (A→B и B→A) → классический deadlock.
    2. sleep(50) создаёт “окно”, в котором оба потока блокируют свои объекты, что делает deadlock почти гарантированным.
    3. Main поток через join() заблокирован до завершения потоков, поэтому при deadlock main тоже “зависает”.

Помни: порядок захвата объектов и вложенные synchronized блоки — ключ к предотвращению deadlock.*/
