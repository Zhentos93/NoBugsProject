package сode_reading.CodeReading_StreamAPI;

import java.util.stream.Stream;

public class A12 {
    public static void main(String[] args) {
        Stream.of(1, 2, 3)
                .map(number -> {
                    System.out.println("map " + number);
                    return number * 10;
                })
                .limit(2)
                .forEach(System.out::println);
    }
}
