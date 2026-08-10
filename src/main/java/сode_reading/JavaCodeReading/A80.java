package сode_reading.JavaCodeReading;

public class A80 {
    public static void main(String[] args) {
        try {
            System.out.println("A");

            int result = 10 / 0;

            System.out.println("B");
        } catch (ArithmeticException exception) {
            System.out.println("C");
        }

        System.out.println("D");
    }
}
