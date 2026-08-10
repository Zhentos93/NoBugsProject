package сode_reading.JavaCodeReading;

import java.util.stream.Stream;

public class A71 {
    public static void main(String[] args) {
        Stream.of(1, 2, 3)
                .peek(System.out::println)
                .map(x -> x * 2);
    }
}
