package сode_reading.JavaCodeReading;

public class A46 {
    public static StringBuilder test() {
        StringBuilder sb = new StringBuilder("A");

        try {
            return sb;
        } finally {
            sb.append("B");
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
