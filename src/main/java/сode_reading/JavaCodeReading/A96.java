package сode_reading.JavaCodeReading;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class User9 {
    String name;

    User9(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User9 other)) {
            return false;
        }

        return name.equals(other.name);
    }
}

public class A96 {
    public static void main(String[] args) {
        Map<User9, String> map = new HashMap<>();

        User9 user9 = new User9("Alex");
        map.put(user9, "QA");

        user9.name = "Max";
        user9.name = "Alex";

        System.out.println(map.get(user9));
    }
}