package practice_7_homework;

import java.util.Scanner;

public class ForTaskSolverHomework {
    public static void main(String[] args) {
        // проверка метода по выводу чисел, которые делятся на 3
        // checkOutputOfNumbersDivisibleBy3();

        // проверка метода по сложению всех чисел от 1 до n (n - вводится с консоли пользователем)
        // checkSumOfNubbers();

        // проверка таблицы умножения для числа, которое пользователь вводит с консоли
        // checkMultiplicationTable();

        // проверка метода по проверке числа на простое/непростое (простое - делится только на себя и на 1. Например, 7)
        // checkSimpleNumber();

        // проверка метода по выводу чисел от 1 до 10
        // checkNumbersFrom1To10();
    }

    public static void checkOutputOfNumbersDivisibleBy3() {
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0) {
                System.out.println(i);
            }
        }
    }

    public static void checkSumOfNubbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        int sum = 0;
        for (int i = 1; i <= number; i++) {
            sum = sum + i;
        }
        System.out.println(sum);
    }

    public static void checkMultiplicationTable() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " * " + i + " = " + number * i);
        }
    }

    public static void checkSimpleNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        boolean isPrime = true;

        for (int i = 2; i <= number - 1; i++) {
            if (number % i == 0) {
                isPrime = false;
                break;
            };
        }
        System.out.println("Число простое?: " + isPrime);
    }

    public static void checkNumbersFrom1To10() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
        }
    }
}
