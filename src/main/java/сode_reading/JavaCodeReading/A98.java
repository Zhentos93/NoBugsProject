package сode_reading.JavaCodeReading;

import java.util.List;
import java.util.Optional;

public class A98 {
    public static void main(String[] args) {
        Optional<Integer> result = List.of(1, 2, 3, 4)
                .stream()
                .filter(number -> {
                    System.out.println("check " + number);
                    return number > 2;
                })
                .findFirst();

        System.out.println(result.orElse(0));
    }
}
