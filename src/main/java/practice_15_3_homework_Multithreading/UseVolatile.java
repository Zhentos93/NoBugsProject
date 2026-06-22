package practice_15_3_homework_Multithreading;

public class UseVolatile {
    static volatile boolean stop;

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            int count = 0;
            while (!stop) {
                count++;
                System.out.println("count: " + count);
            }
        });

        t.start();
        Thread.sleep(2000);
        stop = true;
    }
}
