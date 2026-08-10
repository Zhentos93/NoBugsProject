package сode_reading.JavaCodeReading;

import java.util.*;

class User2 {
    String name;

    User2(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User3)) {
            return false;
        }

        User3 other = (User3) obj;
        return name.equals(other.name);
    }
}

public class A27 {
    public static void main(String[] args) {
        Map<User2, String> map = new HashMap<>();

        User2 user = new User2("Alex");
        map.put(user, "QA");

        user.name = "Max";

        System.out.println(map.get(user));
    }
}
