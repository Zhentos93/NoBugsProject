package practice_13_1_practice_Exceptions_Generics.exceptions.unchecked;

public class ExceptionExample {
    public static void main(String[] args) {
        try {
            int result = divide(10, 0);
            System.out.println("Результат: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: деление на ноль!");
        } finally {
            System.out.println("Операция завершена.");
        }
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Деление на ноль запрещено!");
        }
        return a / b;
    }
}
