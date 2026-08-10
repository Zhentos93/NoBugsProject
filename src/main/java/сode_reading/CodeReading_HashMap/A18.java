package сode_reading.CodeReading_HashMap;

import java.util.HashMap;
import java.util.Map;

class User3 {
    String name;

    User3(String name) {
        this.name = name;
    }
}

public class A18 {
    public static void main(String[] args) {
        User3 user = new User3("Alex");

        Map<String, String> map = new HashMap<>();
        map.put(user.name, "QA");

        user.name = "Max";

        System.out.println(map.get("Alex"));
        System.out.println(map.get(user.name));
    }
}
