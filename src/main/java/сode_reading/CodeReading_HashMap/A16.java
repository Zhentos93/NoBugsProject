package сode_reading.CodeReading_HashMap;

import java.util.HashMap;
import java.util.Map;

class User1 {
    String name;

    User1(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

public class A16 {
    public static void main(String[] args) {
        Map<User1, String> map = new HashMap<>();

        map.put(new User1("Alex"), "QA");

        System.out.println(map.get(new User1("Alex")));
    }
}
