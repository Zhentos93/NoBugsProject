package сode_reading.JavaCodeReading;

public class A76 {
    public static void main(String[] args) {
        Thread thread = new Thread(() ->
                System.out.println(Thread.currentThread().getName())
        );

        thread.run();

        System.out.println(Thread.currentThread().getName());
    }
}
