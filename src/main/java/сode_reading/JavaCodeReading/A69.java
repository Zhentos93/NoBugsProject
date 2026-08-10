package сode_reading.JavaCodeReading;

public class A69 {
    public static void main(String[] args) {
        String first = "Ja" + "va";
        String part = "va";
        String second = "Ja" + part;

        System.out.println(first == "Java");
        System.out.println(second == "Java");
    }
}