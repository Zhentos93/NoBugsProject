package сode_reading.CodeReading_InitializationAndInheritance;

class Parent3 {
    Parent3() {
        print();
    }

    void print() {
        System.out.println("Parent");
    }
}

class Child3 extends Parent3 {
    int number = 5;

    Child3() {
        System.out.println(number);
    }

    @Override
    void print() {
        System.out.println(number);
    }
}

public class A22 {
    public static void main(String[] args) {
        new Child3();
    }
}
