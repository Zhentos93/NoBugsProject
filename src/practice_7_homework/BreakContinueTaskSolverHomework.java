package practice_7_homework;

import java.util.Scanner;

public class BreakContinueTaskSolverHomework {

    public static void main(String[] args) {
        // проверка метода по выводу суммы чисел до первого введённого с консоли отрицательного числа
        // checkSumOfNumbersUpToNegative1();

        // проверка метода, который выводит все числа от 1 до 20 (за исключением тех, которые делятся на 3 без остатка)
        // checkSkippingNumbersDivisibleBy3();

        // проверка метода вывода только положительных чисел
        // checkOutputOnlyPositiveNumbers();

        // проверка метода ввода строк до команды "stop"
         checkEnteringLinesBeforeTheStopCommand();
    }

    public static void checkSumOfNumbersUpToNegative1() {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;

        while (true) {
            System.out.print("Введите число: ");
            int number = scanner.nextInt();

            if (number < 0) {
                break;
            }
            sum = sum + number;
        }
        System.out.println("Сумма: " + sum);
    }

    public static void checkSkippingNumbersDivisibleBy3() {
        for (int i = 1; i <= 20; i++) {
            if (i % 3 == 0) {
                continue;
            }
            System.out.println(i);
        }
    }

    public static void checkOutputOnlyPositiveNumbers() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите число: ");
            int number = scanner.nextInt();

            if (number == 0) {
                break;
            }
            if (number < 0) {
                continue;
            }
            System.out.println(number);
        }
    }

    public static void checkEnteringLinesBeforeTheStopCommand() {
        Scanner scanner = new Scanner(System.in);

        String line;
        while (true) {
            System.out.print("Введите строку: ");
            line = scanner.nextLine();

            if (line.equals("stop"))
                break;
        }
    }
}
