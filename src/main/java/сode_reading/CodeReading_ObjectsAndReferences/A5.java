package сode_reading.CodeReading_ObjectsAndReferences;

class User3 {
    String name;
}

public class A5 {
    public static void main(String[] args) {
        User3 first = new User3();
        first.name = "Alex";

        User3 second = first;
        second.name = "Bob";

        second = new User3();
        second.name = "Max";

        System.out.println(first.name);
        System.out.println(second.name);
    }
}
