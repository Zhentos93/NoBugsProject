package сode_reading.JavaCodeReading;

public class A60 {
    static void test() {
        try {
            System.out.println("try");
            return;
        } finally {
            System.out.println("finally");
        }
    }

    public static void main(String[] args) {
        test();
    }
}
