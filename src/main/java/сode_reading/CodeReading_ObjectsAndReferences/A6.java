package сode_reading.CodeReading_ObjectsAndReferences;

class User4 {
    String name;
}

public class A6 {
    static void change(User4 user) {
        User4 copy = user;
        copy.name = "Bob";

        copy = new User4();
        copy.name = "Max";
    }

    public static void main(String[] args) {
        User4 user = new User4();
        user.name = "Alex";

        change(user);

        System.out.println(user.name);
    }
}