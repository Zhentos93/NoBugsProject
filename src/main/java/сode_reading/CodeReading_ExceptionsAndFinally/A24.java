package сode_reading.CodeReading_ExceptionsAndFinally;

public class A24 {
    static int test() {
        int number = 1;

        try {
            number = 2;
            return number;
        } finally {
            number = 3;
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
