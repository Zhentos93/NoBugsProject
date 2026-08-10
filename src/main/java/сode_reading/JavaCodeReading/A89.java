package сode_reading.JavaCodeReading;

import java.util.List;

public class A89 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4);

        numbers.stream()
                .filter(number -> number % 2 == 0)
                .map(number -> number * 10)
                .forEach(System.out::println);
    }
}
