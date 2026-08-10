package сode_reading.JavaCodeReading;

class User8 {
    String name;
}

public class A91 {
    static void change(User8 user8) {
        user8.name = "Bob";

        user8 = new User8();
        user8.name = "Max";
    }

    public static void main(String[] args) {
        User8 user8 = new User8();
        user8.name = "Alex";

        change(user8);

        System.out.println(user8.name);
    }
}
