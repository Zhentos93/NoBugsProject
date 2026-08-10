package livecoding.algorithms.six;

/*
 * Задача №6. Факториал числа
 *
 * Вычислить факториал числа n: значение n!.
 */

public class FactorialCalculator {

    public FactorialCalculator() {
    }

    int factorial(int n) {
        int result = 1; // Начальное значение произведения

        for (int i = 2; i <= n; i++) { // Перебираем множители от 2 до n
            result *= i;               // Умножаем на текущий множитель
        }

        return result; // Возвращаем вычисленный факториал
    }

    public static void main(String[] args) {
        FactorialCalculator calculator = new FactorialCalculator();

        System.out.println(calculator.factorial(5));
        System.out.println(calculator.factorial(0));
        System.out.println(calculator.factorial(1));
    }
}
