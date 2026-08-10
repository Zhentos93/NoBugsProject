package сode_reading.JavaCodeReading;

public class A54 {
    static class Demo {
        static {
            System.out.println("static");
        }

        Demo() {
            System.out.println("constructor");
        }
    }

    public static void main(String[] args) {
        new Demo();
        new Demo();
    }
}
