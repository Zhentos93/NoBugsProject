package сode_reading.JavaCodeReading;

public class A74 {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() ->
                System.out.print("A")
        );

        t.start();
        t.join();

        System.out.print("B");
    }
}
