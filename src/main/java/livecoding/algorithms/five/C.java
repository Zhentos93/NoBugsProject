package livecoding.algorithms.five;

public class C {

    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        C c = new C();

        System.out.println(c.isPrime(17));
        System.out.println(c.isPrime(9));
        System.out.println(c.isPrime(1));
        System.out.println(c.isPrime(2));
    }
}