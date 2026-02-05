package practice_7_homework;

import java.util.Scanner;

public class WhileTaskSolverHomework {

    public static void main(String[] args) {

        // проверка метода по вычислению факториала по числу, введенного с консоли
        // checkFactorial();

        // проверка метода вывода всех чётных чисел от 1 до числа, введенного пользователем с консоли
        // checkEvenNumbers();

        // проверка метода по обратному отсчёту от введенного числа с консоли до 1
        // checkCountdown();
    }

    public static void checkFactorial() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        int result = 1;
        int i = 1;

        while (i <= number) {
            result = result * i;
            i++;
        }
        System.out.println(result);
    }

    public static void checkEvenNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        int i = 1;

        while (i <= number) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
            i++;
        }
    }

    public static void checkCountdown() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите положительное число: ");
        int number = scanner.nextInt();

        while (number >= 1) {
            System.out.println(number);
            number--;
        }
    }
}