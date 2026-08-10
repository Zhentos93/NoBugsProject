package сode_reading.JavaCodeReading;

import java.util.HashSet;
import java.util.Set;

public class A24 {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        set.add("A");
        set.add("B");
        set.add("A");

        System.out.println(set.size());
        System.out.println(set.contains("A"));
    }
}
