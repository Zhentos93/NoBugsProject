package сode_reading.CodeReading_ObjectsAndReferences;

import java.util.Arrays;

public class A9 {
    static int[] change(int[] numbers) {
        numbers[0] = 9;

        numbers = new int[]{5, 6};

        return numbers;
    }

    public static void main(String[] args) {
        int[] first = {1, 2};

        int[] second = change(first);

        System.out.println(Arrays.toString(first));
        System.out.println(Arrays.toString(second));
    }
}
