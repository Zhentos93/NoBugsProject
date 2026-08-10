package сode_reading.CodeReading_ExceptionsAndFinally;

public class A25 {
    static int test() {
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
