package livecoding.algorithms.fourteen;

/*
 * Задача №14. Обработка деления на ноль
 *
 * Написать метод деления с обработкой
 * исключения деления на ноль.
 */

public class SafeDivider {

    public SafeDivider() {
    }

    int safeDivide(int a, int b) {
        try { // Пытаемся выполнить деление
            return a / b;
        } catch (ArithmeticException e) { // Перехватываем деление на ноль
            return 0;
        }
    }

    public static void main(String[] args) {
        SafeDivider divider = new SafeDivider();

        System.out.println(divider.safeDivide(10, 2));
        System.out.println(divider.safeDivide(7, 2));
        System.out.println(divider.safeDivide(10, 0));
        System.out.println(divider.safeDivide(0, 0));
    }
}
