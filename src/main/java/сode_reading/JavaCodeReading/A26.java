package сode_reading.JavaCodeReading;

import java.util.*;

class User1 {
    String name;

    User1(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User3 other)) {
            return false;
        }

        return name.equals(other.name);
    }
}

public class A26 {
    public static void main(String[] args) {
        Set<User1> users = new HashSet<>();

        users.add(new User1("Alex"));
        users.add(new User1("Alex"));

        System.out.println(users.size());
    }
}
