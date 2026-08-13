package livecoding.algorithms.twentythree;

/*
 * Задача №23. Проверка чётности числа
 *
 * Вернуть true, если число чётное.
 */

public class EvenNumberChecker {

    public EvenNumberChecker() {
    }

    boolean isEven(int n) {
        return n % 2 == 0; // Проверяем отсутствие остатка от деления на 2
    }

    public static void main(String[] args) {
        EvenNumberChecker checker = new EvenNumberChecker();

        System.out.println(checker.isEven(8));
        System.out.println(checker.isEven(7));
        System.out.println(checker.isEven(0));
        System.out.println(checker.isEven(-4));
        System.out.println(checker.isEven(-3));
    }
}