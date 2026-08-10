package сode_reading.JavaCodeReading;

public class A39 {
    static class Parent {
        void print(Object object) {
            System.out.println("Parent Object");
        }
    }

    static class Child extends Parent {
        void print(String string) {
            System.out.println("Child String");
        }
    }

    public static void main(String[] args) {
        Parent object = new Child();

        object.print("Java");
    }
}
