package сode_reading.JavaCodeReading;

class Parent {
    Parent() {
        print();
    }

    void print() {
        System.out.println("Parent");
    }
}

class Child extends Parent {
    String value = "Child";

    @Override
    void print() {
        System.out.println(value);
    }
}

public class A6 {
    public static void main(String[] args) {
        new Child();
    }
}
