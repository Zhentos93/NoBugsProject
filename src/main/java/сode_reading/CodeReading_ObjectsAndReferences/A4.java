package сode_reading.CodeReading_ObjectsAndReferences;

class User2 {
    String name;
}

public class A4 {
    static User2 change(User2 user) {
        user.name = "Bob";

        User2 other = new User2();
        other.name = user.name + " Max";

        return other;
    }

    public static void main(String[] args) {
        User2 first = new User2();
        first.name = "Alex";

        User2 second = change(first);

        System.out.println(first.name);
        System.out.println(second.name);
    }
}
