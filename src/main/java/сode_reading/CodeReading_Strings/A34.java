package сode_reading.CodeReading_Strings;

public class A34 {
    public static void main(String[] args) {
        String first = "Java";
        String second = new String("Java");
        String third = second.intern();

        System.out.println(first == second);
        System.out.println(first == third);
        System.out.println(second.equals(third));
    }
}
