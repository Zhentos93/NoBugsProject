package сode_reading.JavaCodeReading;

import java.util.stream.Stream;

public class A10 {
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4)
                .map(x -> {
                    System.out.println("map " + x);
                    return x * 10;
                })
                .limit(2)
                .forEach(System.out::println);
    }
}
