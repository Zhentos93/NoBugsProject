package сode_reading.JavaCodeReading;

public class A15 {
    static class Parent {
        Parent() {
            System.out.println("Parent constructor");
        }
    }

    static class Child extends Parent {
        Child() {
            System.out.println("Child constructor");
        }
    }

    public static void main(String[] args) {
        new Child();
    }
}
