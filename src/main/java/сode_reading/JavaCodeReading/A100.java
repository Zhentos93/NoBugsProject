package сode_reading.JavaCodeReading;

public class A100 {
    static int test() {
        int result = 1;

        try {
            return result;
        } finally {
            result = 2;
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
