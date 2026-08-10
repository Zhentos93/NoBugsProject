package сode_reading.JavaCodeReading;

import java.util.*;

public class A83 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put("A", 1);
        map.put("A", 2);
        map.put("B", 3);

        System.out.println(map.size());
        System.out.println(map.get("A"));
    }
}
