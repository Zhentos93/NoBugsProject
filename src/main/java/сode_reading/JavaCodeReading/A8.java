package сode_reading.JavaCodeReading;

import java.util.List;

public class A8 {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3);

        numbers.stream()
                .map(number -> {
                    System.out.println(number);
                    return number * 2;
                });

        System.out.println("End");
    }
}
