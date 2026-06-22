package practice_15_3_homework_Multithreading;

public class CreateSingleThread {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Привет из потока!");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();
    }
}
