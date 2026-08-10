package сode_reading.CodeReading_InitializationAndInheritance;

class Parent1 {
    String name = init();

    Parent1() {
        System.out.println("Parent constructor");
    }

    String init() {
        System.out.println("Parent field");
        return "Parent";
    }
}

class Child1 extends Parent1 {
    {
        System.out.println("Child block");
    }

    // Конструктор явно не объявлен
}

public class A20 {
    public static void main(String[] args) {
        new Child1();
    }
}
