package сode_reading.JavaCodeReading;

public class A28 {
    public static void main(String[] args) {
        StringBuilder a = new StringBuilder("Java");
        StringBuilder b = a;

        b.append(" QA");

        System.out.println(a);
    }
}
