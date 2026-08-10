package сode_reading.JavaCodeReading;

public class A51 {
    static class User {
        String name;
    }

    static void change(User user) {
        user = new User();
        user.name = "Bob";
    }

    public static void main(String[] args) {
        User user = new User();
        user.name = "Alex";

        change(user);

        System.out.println(user.name);
    }
}
