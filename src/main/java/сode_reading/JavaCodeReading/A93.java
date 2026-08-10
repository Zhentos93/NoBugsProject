package сode_reading.JavaCodeReading;

class Parent4 {
    Parent4() {
        show();
    }

    void show() {
        System.out.println("Parent: 1");
    }
}

class Child4 extends Parent4 {
    int number = 7;

    Child4() {
        show();
    }

    @Override
    void show() {
        System.out.println("Child: " + number);
    }
}

public class A93 {
    public static void main(String[] args) {
        new Child4();
    }
}
