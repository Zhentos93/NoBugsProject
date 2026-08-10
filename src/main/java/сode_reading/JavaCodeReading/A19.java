package сode_reading.JavaCodeReading;

public class A19 {
    static class User {
        String name;
    }

    public static void main(String[] args) {
        final User user = new User();

        user.name = "Alex";
        user.name = "Bob";

        System.out.println(user.name);
    }
}
