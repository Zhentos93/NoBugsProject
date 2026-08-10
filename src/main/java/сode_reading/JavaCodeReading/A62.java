package сode_reading.JavaCodeReading;

import java.util.Arrays;

public class A62 {
    public static void main(String[] args) {
        int[] first = {1, 2, 3};
        int[] second = first;

        second[0] = 10;

        System.out.println(Arrays.toString(first));
        System.out.println(Arrays.toString(second));
    }
}
