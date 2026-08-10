package сode_reading.JavaCodeReading;

class Parent2 {
    static String name = "Parent";
}

class Child2 extends Parent2 {
    static String name = "Child";
}

public class A79 {
    public static void main(String[] args) {
        Parent2 p = new Child2();

        System.out.println(p.name);
    }
}
