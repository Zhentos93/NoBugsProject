package сode_reading.CodeReading_ObjectsAndReferences;

class User5 {
    String name;

    User5(String name) {
        this.name = name;
    }
}

public class A10 {
    static void swap(User5 first, User5 second) {
        User5 temporary = first;
        first = second;
        second = temporary;
    }

    public static void main(String[] args) {
        User5 first = new User5("Alex");
        User5 second = new User5("Bob");

        swap(first, second);

        System.out.println(first.name);
        System.out.println(second.name);
    }
}
