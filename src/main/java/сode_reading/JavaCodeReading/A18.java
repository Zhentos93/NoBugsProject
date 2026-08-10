package сode_reading.JavaCodeReading;

public class A18 {
    static class Parent {
        static void print() {
            System.out.println("Parent static");
        }
    }

    static class Child extends Parent {
        static void print() {
            System.out.println("Child static");
        }
    }

    public static void main(String[] args) {
        Parent obj = new Child();

        obj.print();
    }
}
