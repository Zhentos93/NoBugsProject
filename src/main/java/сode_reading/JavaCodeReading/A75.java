package сode_reading.JavaCodeReading;

import java.util.List;

public class A75 {
    public static void main(String[] args) {
        List<String> names =
                List.of("Alex", "Bob", "Anna", "Alice");

        long count = names.stream()
                .filter(name -> name.startsWith("A"))
                .count();

        System.out.println(count);
    }
}
