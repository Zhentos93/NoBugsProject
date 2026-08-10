package сode_reading.CodeReading_Overload;

public class A28 {
    static void print(int value) {
        System.out.println("int");
    }

    static void print(double value) {
        System.out.println("double");
    }

    public static void main(String[] args) {
        print('A');
    }
}
