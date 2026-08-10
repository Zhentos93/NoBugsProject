package сode_reading.CodeReading_Overload;

public class A30 {
    static void print(Object value) {
        System.out.println("Object");
    }

    static void print(Integer value) {
        System.out.println("Integer");
    }

    public static void main(String[] args) {
        print(10);
    }
}
