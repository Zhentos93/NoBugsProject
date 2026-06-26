// Напишите тесты для метода, который вычисляет факториал числа:

/*
Тесты должны проверять:
0! = 1
Маленькие числа (1!, 5!, 7!)
Отрицательные числа (должно выбрасываться исключение)*/

package practice_16_3_homework_WritingTests;

public class CalculateFactorial {

    public int factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Negative numbers not allowed");
        return (n == 0) ? 1 : n * factorial(n - 1);
    }
}
