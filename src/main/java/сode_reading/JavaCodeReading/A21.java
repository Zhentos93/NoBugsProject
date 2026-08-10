package сode_reading.JavaCodeReading;

import java.util.*;

public class A21 {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        map.put("A", 10);

        System.out.println(map.getOrDefault("A", 100));
        System.out.println(map.getOrDefault("B", 100));
    }
}
