package сode_reading.CodeReading_HashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    public boolean equals(Object object) {
        if (!(object instanceof User2)) {
            return false;
        }

        User2 other = (User2) object;
        return name.equals(other.name);
    }
}

public class A17 {
    public static void main(String[] args) {
        Map<User2, String> map = new HashMap<>();

        User2 user = new User2("Alex");
        map.put(user, "QA");

        user.name = "Max";

        System.out.println(map.get(user));
    }
}
