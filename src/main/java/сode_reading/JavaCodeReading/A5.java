package сode_reading.JavaCodeReading;

public class A5 {
    public static int test() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
