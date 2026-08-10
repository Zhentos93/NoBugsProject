package сode_reading.JavaCodeReading;

public class A16 {
    static class Parent {
        String name = "Parent";
    }

    static class Child extends Parent {
        String name = "Child";
    }

    public static void main(String[] args) {
        Parent obj = new Child();

        System.out.println(obj.name);
    }
}