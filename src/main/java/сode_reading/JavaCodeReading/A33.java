package сode_reading.JavaCodeReading;

public class A33 {
    public static void main(String[] args)
            throws InterruptedException {

        Thread thread = new Thread(() ->
                System.out.println("Worker"));

        thread.start();
        thread.join();

        System.out.println("Main");
    }
}
