/*Условие задачи:
    Напишите метод, который принимает на вход два числа и выполняет их деление.
    Обработайте ситуацию, когда второе число равно нулю, чтобы избежать исключения при делении.*/

package practice_13_2_homework_Exceptions_Generics.exceptions.unchecked;

public class DivisionByZero {
    public static void main(String[] args) {
        DivisionByZero divisionByZero = new DivisionByZero();
        System.out.println(divisionByZero.divide(10, 0));
    }

    public double divide (int a, int b) {
        try {
            return a / b;
        }
        catch (ArithmeticException e) {
            System.err.println("На ноль делить нельзя");
            return 0;
        }
    }
}
