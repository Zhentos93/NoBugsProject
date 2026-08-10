package сode_reading.JavaCodeReading;

public class A32 {

    static void test() {
        try {
            System.out.println("A");
            throw new RuntimeException();
        } finally {
            System.out.println("B");
        }
    }

    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            System.out.println("C");
        }
    }
}
