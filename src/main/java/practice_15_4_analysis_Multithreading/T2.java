package practice_15_4_analysis_Multithreading;

public class T2 {
    static volatile int x = 0;

    public static void main(String[] args) throws Exception {
        Thread a = new Thread(() -> { for (int i = 0; i < 1_000_000; i++) x++; });
        Thread b = new Thread(() -> { for (int i = 0; i < 1_000_000; i++) x++; });

        a.start(); b.start();
        a.join(); b.join();

        System.out.println(x);
    }
}

/*T2 (volatile int x, гонка данных)
Главное внимание:
        1. volatile гарантирует видимость, но не атомарность → x++ всё ещё небезопасно.
        2. join() нужен, чтобы main дождался завершения потоков перед выводом.
        3. Чтобы избежать потери инкрементов, используйте synchronized или AtomicInteger.

Помни: даже с volatile счётчики могут “терять” инкременты при параллельной работе потоков.*/



