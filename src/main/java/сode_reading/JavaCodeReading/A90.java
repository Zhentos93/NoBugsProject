package сode_reading.JavaCodeReading;

class Parent1 {
    static {
        System.out.println("Parent static");
    }

    {
        System.out.println("Parent block");
    }

    Parent1() {
        System.out.println("Parent constructor");
    }
}

class Child1 extends Parent1 {
    static {
        System.out.println("Child static");
    }

    {
        System.out.println("Child block");
    }

    Child1() {
        System.out.println("Child constructor");
    }
}

public class A90 {
    public static void main(String[] args) {
        new Child1();
        new Child1();
    }
}
