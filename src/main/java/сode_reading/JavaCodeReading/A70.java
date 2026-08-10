package сode_reading.JavaCodeReading;

public class A70 {
    public static int test() {
        try {
            return 10;
        } finally {
            System.out.println("finally");
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
