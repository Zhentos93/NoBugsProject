package practice_15_1_lecture_Multithreading.Deadlock;

class DeadlockFixed {
    private static final Object LOCK1 = new Object();
    private static final Object LOCK2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (LOCK1) { // всегда сначала LOCK1
                System.out.println("Поток 1: захватил LOCK1");
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (LOCK2) {
                    System.out.println("Поток 1: захватил LOCK2");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (LOCK1) { // изменили порядок: сначала LOCK1, потом LOCK2
                System.out.println("Поток 2: захватил LOCK1");
                try { Thread.sleep(100); } catch (InterruptedException e) {}

                synchronized (LOCK2) {
                    System.out.println("Поток 2: захватил LOCK2");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
