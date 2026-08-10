package сode_reading.JavaCodeReading;

public class A48 {
    public static void main(String[] args) {
        String a = "test";
        String b = new String("test");
        String c = b.intern();

        System.out.println(a == b);
        System.out.println(a == c);
    }
}
