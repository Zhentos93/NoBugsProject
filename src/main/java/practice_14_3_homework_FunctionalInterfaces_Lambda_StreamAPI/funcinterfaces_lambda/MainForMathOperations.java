/*Задача: Напишите интерфейс MathOperation, который принимает два числа и возвращает результат операции.
Реализуйте его с помощью лямбда-выражений: сложение, вычитание, умножение, деление.*/

package practice_14_3_homework_FunctionalInterfaces_Lambda_StreamAPI.funcinterfaces_lambda;

public class MainForMathOperations {
    public static void main(String[] args) {
        MathOperation add = Double::sum;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiply = (a, b) -> a * b;
        MathOperation divide = (a, b) -> a / b;

        System.out.println(add.operate(5, 9));
        System.out.println(subtraction.operate(4, 5));
        System.out.println(multiply.operate(3, 7));
        System.out.println(divide.operate(3, 1));
    }
}
