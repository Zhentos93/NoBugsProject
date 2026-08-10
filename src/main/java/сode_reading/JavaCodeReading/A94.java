package сode_reading.JavaCodeReading;

import java.util.Arrays;

public class A94 {
    static void change(int[] numbers) {
        numbers[0] = 9;

        numbers = new int[]{5, 6};
        numbers[1] = 8;
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2};

        change(numbers);

        System.out.println(Arrays.toString(numbers));
    }
}
