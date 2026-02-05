package practice_7_homework;

import java.util.Scanner;

public class DoWhileTaskSolverHomework {

    public static void main(String[] args) {
        // проверка метода запроса положительного числа
        // checkPositiveNumber();

        // проверка метода по вводу корректного пароля
        // checkPassword();

        // проверка метода по выводу чисел от 1 до 10
        // checkOutputNumbers();

        // проверка метода по завершению программы через "exit"
        // checkExit();

        // проверка метода по подсчету количества цифр в числе
        // checkCountNumbers();
    }

    public static void checkPositiveNumber() {
        Scanner scanner = new Scanner(System.in);

        int number;

        do {
            System.out.print("Введите число: ");
            number = scanner.nextInt();
        } while (number <= 0);
    }

    public static void checkPassword() {
        Scanner scanner = new Scanner(System.in);

        String password;
        String correctPassword = "1234";

        do {
            System.out.print("Введите пароль: ");
            password = scanner.nextLine();
        } while (!password.equals(correctPassword));
    }

    public static void checkOutputNumbers() {

        int i = 1;

        do {
            System.out.println(i);
            i++;
        } while (i<=10);
    }

    public static void checkExit() {
        Scanner scanner = new Scanner(System.in);

        String command;

        do {
            System.out.print("Введите команду: ");
            command = scanner.nextLine();
        } while (!command.equals("exit"));
    }

    public static void checkCountNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int n = scanner.nextInt();

        int count = 0;

        do {
            n = n / 10; // более подробно ознакомиться с этой конструкцией
            count++;
        } while (n != 0);

        System.out.println(count);
    }
}
