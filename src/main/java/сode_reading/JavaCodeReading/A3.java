package сode_reading.JavaCodeReading;

import java.util.*;

public class A3 {
    public static void main(String[] args) {
        List<Integer> nums =
                new ArrayList<>(List.of(1, 2, 3, 4, 5));

        nums.remove(2);
        nums.remove(Integer.valueOf(4));

        System.out.println(nums);
    }
}