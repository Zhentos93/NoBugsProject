package livecoding.algorithms.fourtyfour;

/*
 * Задача №44. Факториал рекурсивно
 *
 * Вернуть факториал числа n рекурсивно.
 */

public class RecursiveFactorialCalculator {

    public RecursiveFactorialCalculator() {
    }

    public int factorial(int n) {
        if (n <= 1) { // Базовый случай рекурсии
            return 1;
        }

        return n * factorial(n - 1); // Рекурсивно уменьшаем n
    }

    public static void main(String[] args) {
        RecursiveFactorialCalculator calculator =
                new RecursiveFactorialCalculator();

        System.out.println(calculator.factorial(5));
        System.out.println(calculator.factorial(3));
        System.out.println(calculator.factorial(1));
        System.out.println(calculator.factorial(0));
        System.out.println(calculator.factorial(-2));
    }
}
