package сode_reading.CodeReading_InitializationAndInheritance;

class Parent {
    static {
        System.out.println("A");
    }

    {
        System.out.println("B");
    }

    Parent() {
        System.out.println("C");
    }
}

class Child extends Parent1 {
    static {
        System.out.println("D");
    }

    {
        System.out.println("E");
    }

    Child() {
        System.out.println("F");
    }
}

public class A19 {
    public static void main(String[] args) {
        new Child1();
    }
}
