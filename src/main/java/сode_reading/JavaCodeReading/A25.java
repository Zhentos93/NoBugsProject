package сode_reading.JavaCodeReading;

import java.util.*;

public class A25 {
    static class User {
        String name;

        User(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Set<User> users = new HashSet<>();

        users.add(new User("Alex"));
        users.add(new User("Alex"));

        System.out.println(users.size());
    }
}