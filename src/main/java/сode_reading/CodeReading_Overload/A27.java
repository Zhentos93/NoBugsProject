package сode_reading.CodeReading_Overload;

public class A27 {
    static void print(long value) {
        System.out.println("long");
    }

    static void print(Integer value) {
        System.out.println("Integer");
    }

    public static void main(String[] args) {
        print(10);
    }
}
