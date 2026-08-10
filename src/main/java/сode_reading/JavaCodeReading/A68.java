package сode_reading.JavaCodeReading;

public class A68 {
    static class A {
        A() {
            System.out.println("A");
        }
    }

    static class B extends A {
        B() {
            System.out.println("B");
        }
    }

    static class C extends B {
        C() {
            System.out.println("C");
        }
    }

    public static void main(String[] args) {
        new C();
    }
}
