package сode_reading.JavaCodeReading;

import java.util.*;

public class A22 {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "One");
        map.put(1, "New");

        System.out.println(map.get(1));
    }
}
