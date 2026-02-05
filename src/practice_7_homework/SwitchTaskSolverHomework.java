package practice_7_homework;

import java.util.Scanner;

public class SwitchTaskSolverHomework {

    public static void main(String[] args) {
        // метод по проверке дня недели
        // checkWeekOfDay();

        // метод по проверке цены билета в зависимости от дня недели
        // checkTicketPrice();

        // метод по переводу числовых оценок в буквенные (A–F)
        // convertingNumericalGradesToLetterGrades();

        // метод проверки состояния системы
        // checkStatusOfSystem();

        // метод проверки работы простого калькулятора
        // checkSimpleCalculator();
    }

    public static void checkWeekOfDay() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите день недели: ");
        int day = scanner.nextInt();

        switch (day) {
            case 1:
                System.out.println("Понедельник");
                break;
            case 2:
                System.out.println("Вторник");
                break;
            case 3:
                System.out.println("Среда");
                break;
            case 4:
                System.out.println("Четверг");
                break;
            case 5:
                System.out.println("Пятница");
                break;
            case 6:
                System.out.println("Суббота");
                break;
            case 7:
                System.out.println("Воскресенье");
                break;
            default:
                System.out.println("Неверный день");
        }
    }

    public static void checkTicketPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите день недели: ");
        int day = scanner.nextInt();

        switch (day) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("Цена билета: 300 рублей");
                break;
            case 6:
            case 7:
                System.out.println("Цена билета: 450 рублей");
                break;
            default:
                System.out.println("404");
        }
    }

    public static void convertingNumericalGradesToLetterGrades() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        if (number >= 90 && number <= 100) {
            System.out.println("Оценка A");
        } else if (number >= 80 && number < 90) {
            System.out.println("Оценка B");
        } else if (number >= 70 && number < 80) {
            System.out.println("Оценка C");
        } else if (number >= 60 && number < 70)
            System.out.println("Оценка D");
        else if (number >= 0 && number < 60) {
            System.out.println("Оценка F");
        } else System.out.println("Такой оценки не существует");
    }

    public static void checkStatusOfSystem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите статус: ");
        String cmd = scanner.nextLine();

        switch (cmd) {
            case "start":
                System.out.println("Система запущена");
                break;
            case "stop":
                System.out.println("Система остановлена");
                break;
            case "restart":
                System.out.println("Система перезапущена");
                break;
            case "status":
                System.out.println("Статус системы");
                break;
            default:
                System.out.println("Несуществующая команда");
        }
    }

    public static void checkSimpleCalculator() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите 1 число: ");
        int a = scanner.nextInt();
        System.out.print("Введите 2 число: ");
        int b = scanner.nextInt();
        System.out.print("Введите операцию: ");
        scanner.nextLine();
        String symbol = scanner.nextLine();

        switch (symbol) {
            case "+":
                int addition = a + b;
                System.out.println("Результат: " + addition);
                break;
            case "-":
                int subtraction = a - b;
                System.out.println("Результат: " + subtraction);
                break;
            case "*":
                int multiplication = a * b;
                System.out.println("Результат: " + multiplication);
                break;
            case "/":
                if (b == 0) {
                    System.out.println("Ошибка: делить на 0 нельзя!");
                } else {
                    double division = a * 1.0 / b;
                    System.out.println("Результат: " + division);
                }
                break;
        }
    }
}
