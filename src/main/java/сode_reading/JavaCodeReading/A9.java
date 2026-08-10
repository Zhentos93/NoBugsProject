package сode_reading.JavaCodeReading;

import java.util.List;

public class A9 {
    public static void main(String[] args) {
        List.of(1, 2, 3, 4)
                .stream()
                .filter(x -> {
                    System.out.println("filter " + x);
                    return x % 2 == 0;
                })
                .findFirst();

        System.out.println("done");
    }
}
