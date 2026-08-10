package сode_reading.CodeReading_ObjectsAndReferences;

import java.util.Arrays;

public class A2 {
    static void change(int[] first, int[] second) {
        first[0] = 5;

        second = first;
        second[1] = 6;
    }

    public static void main(String[] args) {
        int[] first = {1, 2};
        int[] second = {3, 4};

        change(first, second);

        System.out.println(Arrays.toString(first));
        System.out.println(Arrays.toString(second));
    }
}
