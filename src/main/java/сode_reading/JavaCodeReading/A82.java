package сode_reading.JavaCodeReading;

public class A82 {
    public static void main(String[] args) {
        String a = "Java";
        String b = "Ja" + "va";
        String c = new String("Java");

        System.out.println(a == b);
        System.out.println(a == c);
        System.out.println(a.equals(c));
    }
}
