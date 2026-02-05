package practice_7_homework;

import java.util.Scanner;

public class IfElseTaskSolverHomework {

    public static void main(String[] args) {
        // проверка метода по определению знака числа
        // checkPositiveNegativeOrZeroOfNumber();

        // проверка метода по поиску наибольшего из двух чисел
        // checkMaxOfTwoNumbers();

        // проверка метода вывода оценки по шкале 1-5
        // checkGrade();

        // проверка метода четности/нечетности введенного числа
        // checkParity();

        // проверка метода по определению скидки в зависимости от возраста
        // checkDiscount();

        // проверка метода по проверке оценки результата теста
        // checkGradeOfTestResults();
    }

    public static void checkPositiveNegativeOrZeroOfNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        if (number > 0) {
            System.out.println("Число положительное");
        } else if (number < 0) {
            System.out.println("Число отрицательное");
        } else System.out.println("Число равно нулю");
    }

    public static void checkMaxOfTwoNumbers() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите первое число: ");
        int a = scanner.nextInt();
        System.out.print("Введите второе число: ");
        int b = scanner.nextInt();

        if (a > b) {
            System.out.println("Наибольшее число: " + a);
        } else if (a == b) {
            System.out.println("Числа равны");
        }
        else System.out.println("Наибольшее число: " + b);
        // System.out.println("Наибольшее из двух чисел: " + Math.max(a,b)); - альтернативный вариант решения задачи
    }

    public static void checkGrade() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        if (number == 5) {
            System.out.println("Отлично");
        } else if (number == 4) {
            System.out.println("Хорошо");
        } else if (number == 3) {
            System.out.println("Удовлетворительно");
        } else if (number == 2 || number == 1) {
            System.out.println("Неудовлетворительно");
        } else {
            System.out.println("Такой оценки не существует");
        }
    }

    public static void checkParity() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        if (number % 2 == 0) {
            System.out.println("Четное");
        } else System.out.println("Нечетное");
    }

    public static void checkDiscount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ваш возраст: ");
        int age = scanner.nextInt();

        if (age >= 0 && age < 18) {
            System.out.println("Размер скидки: 25%");
        } else if (age >= 65) {
            System.out.println("Размер скидки: 30%");
        } else System.out.println("Размер скидки: без скидки");
    }

    public static void checkGradeOfTestResults() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Сколько баллов вы набрали? ");
        int grade = scanner.nextInt();

        if (grade >= 90 && grade <= 100) {
            System.out.println("Отлично");
        } else if (grade >=75 && grade <=89) {
            System.out.println("Хорошо");
        } else if (grade >=60 && grade <=74) {
            System.out.println("Удовлетворительно");
        } else if (grade >= 0 && grade < 60)
            System.out.println("Неудовлетворительно");
        else System.out.println("Такой оценки не существует");
    }
}