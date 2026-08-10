package сode_reading.CodeReading_Overload;

public class A29 {
    static void print(Object value) {
        System.out.println("Object");
    }

    static void print(CharSequence value) {
        System.out.println("CharSequence");
    }

    static void print(String value) {
        System.out.println("String");
    }

    public static void main(String[] args) {
        print(null);
    }
}
