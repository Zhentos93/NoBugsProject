package livecoding.algorithms.five;

/*
 * Задача №5. Проверка на простое число
 *
 * Определить, является ли число простым:
 * делится ли оно только на 1 и на само себя.
 */

// Ошибки
/*
 * Ошибки в задаче №5 — проверка на простое число:
 *
 * 1. В заголовке for использовались запятые, а переменная i
 *    не была объявлена:
 *    for (i = 2, i * i < n, i++)
 *
 *    Правильно:
 *    for (int i = 2; i * i <= n; i++)
 *
 * 2. В условии цикла использовалось строгое сравнение:
 *    i * i < n
 *
 *    Нужно:
 *    i * i <= n
 *
 *    Иначе числа 4 и 9 могут быть ошибочно признаны простыми.
 */

public class PrimeNumber {
    public PrimeNumber() {
    }

    boolean isPrime(int n) {
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
        PrimeNumber checker = new PrimeNumber();

        System.out.println(checker.isPrime(17));
        System.out.println(checker.isPrime(9));
        System.out.println(checker.isPrime(1));
        System.out.println(checker.isPrime(2));
    }
}
