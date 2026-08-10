package сode_reading.JavaCodeReading;

public class A34 {
    public static void main(String[] args) {
        Thread thread = new Thread(() ->
                System.out.println("Child"));

        thread.start();

        System.out.println("Main");
    }
}
