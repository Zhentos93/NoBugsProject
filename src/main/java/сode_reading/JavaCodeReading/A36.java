package сode_reading.JavaCodeReading;

public class A36 {
    static int counter = 0;

    public static void main(String[] args)
            throws InterruptedException {

        Thread first = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter++;
            }
        });

        Thread second = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter++;
            }
        });

        first.start();
        second.start();

        first.join();
        second.join();

        System.out.println(counter);
    }
}
