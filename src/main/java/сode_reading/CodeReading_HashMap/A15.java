package сode_reading.CodeReading_HashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class User {
    String name;

    User(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User1)) {
            return false;
        }

        User1 other = (User1) object;
        return name.equals(other.name);
    }
}

public class A15 {
    public static void main(String[] args) {
        Map<User1, String> map = new HashMap<>();

        map.put(new User1("Alex"), "QA");

        System.out.println(map.get(new User1("Alex")));
    }
}
