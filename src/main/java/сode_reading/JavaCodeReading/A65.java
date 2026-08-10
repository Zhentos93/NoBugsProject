package сode_reading.JavaCodeReading;

import java.util.List;

public class A65 {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 4, 5);

        int result = nums.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * 10)
                .reduce(0, Integer::sum);

        System.out.println(result);
    }
}
