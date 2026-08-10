package сode_reading.CodeReading_StreamAPI;

import java.util.stream.Stream;

public class A14 {
    public static void main(String[] args) {
        boolean result = Stream.of(1, 2, 3)
                .map(number -> {
                    System.out.println("map " + number);
                    return number * 2;
                })
                .anyMatch(number -> number > 3);

        System.out.println(result);
    }
}
