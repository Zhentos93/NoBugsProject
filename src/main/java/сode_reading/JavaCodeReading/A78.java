package сode_reading.JavaCodeReading;

public class A78 {
    public static void main(String[] args) {
        try {
            int x = 10 / 0;
            System.out.println(x);
        } catch (ArithmeticException e) {
            System.out.println("Error");
        }
    }
}
