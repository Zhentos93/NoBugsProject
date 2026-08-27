package livecoding.algorithms.six;

public class D {

    public int factorial(int n) {
        int result = 1;

        for (int i = 2; i <= n; i++) {
            result *= i;
        }

        return result;
    }

    public static void main(String[] args) {
        D d = new D();

        System.out.println(d.factorial(5));
        System.out.println(d.factorial(0));
        System.out.println(d.factorial(1));
    }
}
