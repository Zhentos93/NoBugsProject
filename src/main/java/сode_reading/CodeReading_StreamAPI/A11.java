package сode_reading.CodeReading_StreamAPI;

import java.util.List;
import java.util.Optional;

public class A11 {
    public static void main(String[] args) {
        Optional<Integer> result = List.of(1, 2, 3)
                .stream()
                .map(number -> {
                    System.out.println("map " + number);
                    return number * 10;
                })
                .findFirst();

        System.out.println(result.orElse(0));
    }
}
