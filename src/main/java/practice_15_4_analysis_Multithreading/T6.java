package practice_15_4_analysis_Multithreading;

public class T6 {
    static ThreadLocal<Integer> tl = ThreadLocal.withInitial(() -> 0); // ThreadLocal -> 0

    public static void main(String[] args) throws Exception {
        Runnable r = () -> {
            tl.set(tl.get() + 1);
            tl.set(tl.get() + 1);
            System.out.println(Thread.currentThread().getName() + "=" + tl.get());
        };

        Thread t1 = new Thread(r, "A");
        Thread t2 = new Thread(r, "B");
        t1.start(); t2.start();
        t1.join(); t2.join();

        System.out.println("main=" + tl.get());
    }
}

/*T6 — ThreadLocal
Главное внимание:
    1. ThreadLocal создаёт отдельное хранилище данных для каждого потока, поэтому изменения одного потока
    не влияют на другой.
    2. Потоки t1 и t2 увеличивают свои локальные значения независимо, а main остаётся с изначальным значением 0.
    3. Использование tl.get() и tl.set() внутри потоков демонстрирует изоляцию данных и безопасность в многопоточности
    без синхронизации.

Обрати внимание: порядок вывода значений A и B может меняться из-за планировщика потоков, но изоляция данных сохраняется.*/
