package practice_15_1_lecture_Multithreading.CallableFuture;

public class FourThreadsExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("Поток 1: работа");
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            System.out.println("Поток 1: завершение");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Поток 2: работа");
            try { Thread.sleep(1000); } catch (InterruptedException e) {}
            System.out.println("Поток 2: завершение");
        });

        Thread t3 = new Thread(() -> {
            System.out.println("Поток 3: работа");
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            System.out.println("Поток 3: завершение");
        });

        Thread t4 = new Thread(() -> {
            System.out.println("Поток 4: работа");
            try { Thread.sleep(1500); } catch (InterruptedException e) {}
            System.out.println("Поток 4: завершение");
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        System.out.println("Main: ждём завершения всех потоков...");
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        System.out.println("Main: все потоки завершены");
    }
}
