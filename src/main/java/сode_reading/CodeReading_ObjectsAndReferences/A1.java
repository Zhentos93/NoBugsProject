package сode_reading.CodeReading_ObjectsAndReferences;

class User {
    String name;
}

public class A1 {
    static void change(User1 first, User1 second) {
        first.name = "Bob";

        second = first;
        second.name = "Max";
    }

    public static void main(String[] args) {
        User1 first = new User1();
        first.name = "Alex";

        User1 second = new User1();
        second.name = "Kate";

        change(first, second);

        System.out.println(first.name);
        System.out.println(second.name);
    }
}
