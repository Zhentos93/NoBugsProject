package сode_reading.JavaCodeReading;

public class A86 {
    static class User {
        String name;
    }

    static void change(User user) {
        user.name = "Bob";
    }

    public static void main(String[] args) {
        User user = new User();
        user.name = "Alex";

        change(user);

        System.out.println(user.name);
    }
}
