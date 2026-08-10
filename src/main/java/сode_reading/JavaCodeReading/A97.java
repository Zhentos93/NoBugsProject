package сode_reading.JavaCodeReading;

import java.util.ArrayList;
import java.util.List;

public class A97 {
    public static void main(String[] args) {
        List<Integer> numbers =
                new ArrayList<>(List.of(10, 20, 30, 40));

        int index = 1;
        numbers.remove(index);

        Integer value = 30;
        numbers.remove(value);

        System.out.println(numbers);
    }
}
