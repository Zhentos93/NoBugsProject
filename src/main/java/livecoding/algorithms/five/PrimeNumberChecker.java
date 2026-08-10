package livecoding.algorithms.five;

/*
 * Задача №5. Проверка на простое число
 *
 * Определить, является ли число простым:
 * делится ли оно только на 1 и на само себя.
 */

public class PrimeNumberChecker {

    public PrimeNumberChecker() {
    }

    boolean isPrime(int n) {
        if (n <= 1) { // Числа меньше 2 не являются простыми
            return false;
        }

        for (int i = 2; i * i <= n; i++) { // Проверяем делители до √n
            if (n % i == 0) {              // Нашли делитель без остатка
                return false;
            }
        }

        return true; // Делители не найдены — число простое
    }

    public static void main(String[] args) {
        PrimeNumberChecker checker = new PrimeNumberChecker();

        System.out.println(checker.isPrime(17));
        System.out.println(checker.isPrime(9));
        System.out.println(checker.isPrime(1));
        System.out.println(checker.isPrime(2));
    }
}