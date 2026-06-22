package practice_15_3_homework_Multithreading;

public class CreateTwoThreads {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println("A");
                    Thread.sleep(500);
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println("B");
                    Thread.sleep(500);
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
    }
}
