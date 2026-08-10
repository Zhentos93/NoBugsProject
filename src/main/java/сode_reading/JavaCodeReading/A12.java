package сode_reading.JavaCodeReading;

public class A12 {
    static void print(Object o) {
        System.out.println("Object");
    }

    static void print(String s) {
        System.out.println("String");
    }

    public static void main(String[] args) {
        print(null);
    }
}
