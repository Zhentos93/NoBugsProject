package livecoding.algorithms.nineteen;

/*
 * Задача №19. Проверка корректности скобочной последовательности
 *
 * Проверить, сбалансированы ли круглые скобки в строке.
 */

public class ParenthesesBalanceChecker {

    public ParenthesesBalanceChecker() {
    }

    boolean isBalanced(String s) {
        int balance = 0; // Текущий баланс открывающих скобок

        for (char c : s.toCharArray()) { // Проверяем символы по очереди
            if (c == '(') {
                balance++; // Учитываем открывающую скобку
            } else if (c == ')') {
                balance--; // Закрываем одну открытую скобку
            }

            if (balance < 0) { // Закрывающей скобке не хватает пары
                return false;
            }
        }

        return balance == 0; // Все открывающие скобки должны закрыться
    }

    public static void main(String[] args) {
        ParenthesesBalanceChecker checker =
                new ParenthesesBalanceChecker();

        System.out.println(checker.isBalanced("(())"));
        System.out.println(checker.isBalanced("(()())"));
        System.out.println(checker.isBalanced("(()"));
        System.out.println(checker.isBalanced(")("));
        System.out.println(checker.isBalanced(""));
        System.out.println(checker.isBalanced("(a + b)"));
    }
}