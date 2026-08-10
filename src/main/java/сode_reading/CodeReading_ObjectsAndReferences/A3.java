package сode_reading.CodeReading_ObjectsAndReferences;

class User1 {
    String name;
}

public class A3 {
    static User1 change(User1 user) {
        user = new User1();
        user.name = "Bob";

        return user;
    }

    public static void main(String[] args) {
        User1 user = new User1();
        user.name = "Alex";

        change(user);

        System.out.println(user.name);
    }
}
