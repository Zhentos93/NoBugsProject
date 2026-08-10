package сode_reading.JavaCodeReading;

public class A73 {
    public static void main(String[] args) {
        Thread t = new Thread(() ->
                System.out.print("A")
        );

        t.start();

        System.out.print("B");
    }
}