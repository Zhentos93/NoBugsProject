package сode_reading.CodeReading_StreamAPI;

import java.util.stream.Stream;

public class A13 {
    public static void main(String[] args) {
        long count = Stream.of(1, 2, 3, 4)
                .filter(number -> {
                    System.out.println("check " + number);
                    return number % 2 == 0;
                })
                .limit(1)
                .count();

        System.out.println(count);
    }
}
