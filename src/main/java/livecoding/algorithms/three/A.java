package livecoding.algorithms.three;

public class A {

    public int sum(int[] numbers) {
        int sum = 0;

        for (int num : numbers) {
            sum += num;
        }

        return sum;
    }

    public static void main(String[] args) {
        A a = new A();

        System.out.println(a.sum(new int[]{1, 2, 3, 4}));
        System.out.println(a.sum(new int[]{-5, 2, 3}));
        System.out.println(a.sum(new int[]{}));
    }
}
