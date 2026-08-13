package livecoding.algorithms.six;

/*
 * Задача №6. Факториал числа
 *
 * Вычислить факториал числа n: значение n!.
 */

// Ошибки
/*
 * Ошибки в задаче №6 — факториал числа:
 *
 * 1. Накопитель произведения был равен 0:
 *    int result = 0;
 *
 *    При умножении нуля на любое число результат остаётся нулём.
 *
 *    Правильно:
 *    int result = 1;
 *
 * 2. Цикл начинался с 1, хотя в примере он начинается с 2:
 *    for (int i = 2; i <= n; i++)
 *
 *    Умножение на 1 не изменяет результат.
 */

public class FactorialCalc {
    public FactorialCalc() {
    }

    int factorial(int n) {
        int result = 1;

        for (int i = 2; i <= n; i++) {
            result *= i;
        }

        return result;
    }

    public static void main(String[] args) {
        FactorialCalc factorialCalc = new FactorialCalc();

        System.out.println(factorialCalc.factorial(5));
        System.out.println(factorialCalc.factorial(0));
        System.out.println(factorialCalc.factorial(1));
    }
}
