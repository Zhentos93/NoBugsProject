package сode_reading.JavaCodeReading;

public class A77 {
    public static void main(String[] args) {
        Thread thread = new Thread(() ->
                System.out.println(Thread.currentThread().getName())
        );

        thread.start();

        System.out.println(Thread.currentThread().getName());
    }
}
