package сode_reading.JavaCodeReading;

import java.util.List;

class User7 {
    String name;

    User7(String name) {
        this.name = name;
    }
}

public class A67 {
    public static void main(String[] args) {
        List<User7> users = List.of(
                new User7("Alex"),
                new User7("Alex"),
                new User7("Maria")
        );

        long count = users.stream()
                .distinct()
                .count();

        System.out.println(count);
    }
}
