package сode_reading.JavaCodeReading;

class Parent3 {
    String parentName = init("Parent field");

    {
        System.out.println("Parent block");
    }

    Parent3() {
        System.out.println("Parent constructor");
    }

    static String init(String text) {
        System.out.println(text);
        return text;
    }
}

class Child3 extends Parent3 {
    String childName = init("Child field");

    {
        System.out.println("Child block");
    }

    Child3() {
        System.out.println("Child constructor");
    }
}

public class A92 {
    public static void main(String[] args) {
        new Child3();
    }
}
