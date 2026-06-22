package practice_15_4_analysis_Multithreading;

public class T5 {
    static final Object lock = new Object();
    static boolean ready = false;

    public static void main(String[] args) throws Exception {
        Thread waiter = new Thread(() -> {
            synchronized (lock) {
                while (!ready) {
                    try { lock.wait(); } catch (InterruptedException ignored) {}
                }
                System.out.println("go");
            }
        });

        Thread notifier = new Thread(() -> {
            synchronized (lock) {
                ready = true;
                lock.notify();
            }
        });

        waiter.start();
        notifier.start();

        waiter.join();
        notifier.join();
    }
}

/*T5 — wait/notify и синхронизация
Главное внимание:
    1. Поток waiter ждёт (wait()), пока флаг ready не станет true, и отпускает lock, позволяя другим потокам работать.
    2. Поток notifier устанавливает ready = true и вызывает notify() → пробуждает waiter, но waiter продолжает только
    после повторного захвата lock.
    3. Проверка if (!ready) защищает от ситуации, когда notify срабатывает раньше, чем waiter начал ждать.

Важно: понимание того, что wait() освобождает lock, а notify() сигнализирует, но поток продолжает только после
повторного захвата lock, — ключевой момент работы с wait/notify.*/