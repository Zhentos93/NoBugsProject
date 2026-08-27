package livecoding.algorithms.four;

public class B {

    public int findMax(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException();
        }

        int max = numbers[0];

        for (int num : numbers) {
            if (num > max) {
                max = num;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        B b = new B();

        System.out.println(b.findMax(new int[]{3, 8, 2, 5}));
        System.out.println(b.findMax(new int[]{-7, -2, -10}));
        System.out.println(b.findMax(new int[]{4}));
    }
}
