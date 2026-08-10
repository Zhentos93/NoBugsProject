package сode_reading.JavaCodeReading;

public class A1 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("A");

        change(sb);

        System.out.println(sb);
    }

    static void change(StringBuilder sb) {
        sb.append("B");
        sb = new StringBuilder("C");
        sb.append("D");
    }
}
