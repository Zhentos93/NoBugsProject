package livecoding.algorithms.fourteen;

/*
 * Задача №14. Обработка деления на ноль
 *
 * Написать метод деления с обработкой
 * исключения деления на ноль.
 */

// Ошибок при решении нет

public class SafeDivide {
    public SafeDivide() {
    }

    public int safeDivide(int a, int b) {
        try {
            return a / b;
        } catch (ArithmeticException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        SafeDivide safeDivide = new SafeDivide();

        System.out.println(safeDivide.safeDivide(10, 2));
        System.out.println(safeDivide.safeDivide(7, 2));
        System.out.println(safeDivide.safeDivide(10, 0));
        System.out.println(safeDivide.safeDivide(0, 0));
    }
}
