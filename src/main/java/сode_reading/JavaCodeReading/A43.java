package сode_reading.JavaCodeReading;

import java.util.Arrays;

public class A43 {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3};

        change(numbers);

        System.out.println(Arrays.toString(numbers));
    }

    static void change(int[] numbers) {
        numbers[0] = 10;
    }
}
