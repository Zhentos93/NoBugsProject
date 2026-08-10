package сode_reading.CodeReading_Strings;

public class A33 {
    public static void main(String[] args) {
        String first = "Java";
        String second = "Ja" + "va";

        String part = "va";
        String third = "Ja" + part;

        System.out.println(first == second);
        System.out.println(first == third);
    }
}
