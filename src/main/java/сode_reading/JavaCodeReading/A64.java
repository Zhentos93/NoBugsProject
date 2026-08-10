package сode_reading.JavaCodeReading;

import java.util.List;

public class A64 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        long count = numbers.stream()
                .filter(number -> number % 2 == 0)
                .count();

        System.out.println(count);
    }
}
