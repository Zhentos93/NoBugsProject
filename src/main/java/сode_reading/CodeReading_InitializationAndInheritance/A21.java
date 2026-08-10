package сode_reading.CodeReading_InitializationAndInheritance;

class Parent2 {
    static {
        System.out.println("Parent static");
    }

    String name = init("Parent field");

    Parent2() {
        System.out.println("Parent constructor");
    }

    static String init(String text) {
        System.out.println(text);
        return text;
    }
}

class Child2 extends Parent2 {
    static {
        System.out.println("Child static");
    }

    String name = init("Child field");

    Child2() {
        System.out.println("Child constructor");
    }
}

public class A21 {
    public static void main(String[] args) {
        new Child2();
        new Child2();
    }
}
