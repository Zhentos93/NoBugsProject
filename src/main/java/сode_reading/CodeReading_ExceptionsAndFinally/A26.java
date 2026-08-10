package сode_reading.CodeReading_ExceptionsAndFinally;

public class A26 {
    static StringBuilder test() {
        StringBuilder builder = new StringBuilder("A");

        try {
            return builder;
        } finally {
            builder.append("B");
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
