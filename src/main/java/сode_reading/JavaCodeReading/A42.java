package сode_reading.JavaCodeReading;

import java.util.List;

public class A42 {
    public static void main(String[] args) {
        List<String> words = List.of(
                "java",
                "python",
                "go",
                "java",
                "kotlin"
        );

        long result = words.stream()
                .filter(s -> s.length() > 3)
                .distinct()
                .map(String::toUpperCase)
                .peek(System.out::println)
                .count();

        System.out.println("Result = " + result);
    }
}
