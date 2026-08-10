package сode_reading.JavaCodeReading;

class User {
    String name = getName();

    User() {
        System.out.println("constructor");
    }

    String getName() {
        System.out.println("field init");
        return "Alex";
    }
}

public class A11 {
    public static void main(String[] args) {
        new User8();
    }
}
