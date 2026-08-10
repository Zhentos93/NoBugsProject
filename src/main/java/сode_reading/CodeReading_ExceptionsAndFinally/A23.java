package сode_reading.CodeReading_ExceptionsAndFinally;

public class A23 {
    public static void main(String[] args) {
        try {
            System.out.println("A");

            int number = 10 / 0;

            System.out.println("B");
        } catch (ArithmeticException exception) {
            System.out.println("C");
        } finally {
            System.out.println("D");
        }

        System.out.println("E");
    }
}
