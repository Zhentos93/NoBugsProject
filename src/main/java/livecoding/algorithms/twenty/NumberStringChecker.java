package livecoding.algorithms.twenty;

/*
 * Задача №20. Проверка строки на число
 *
 * Проверить, можно ли строку безопасно
 * преобразовать в целое число.
 */

public class NumberStringChecker {

    public NumberStringChecker() {
    }

    boolean isNumber(String s) {
        try {
            Integer.parseInt(s); // Пытаемся преобразовать строку в int
            return true;         // Преобразование прошло успешно
        } catch (NumberFormatException e) {
            return false; // Строка не является корректным числом
        }
    }

    public static void main(String[] args) {
        NumberStringChecker checker = new NumberStringChecker();

        System.out.println(checker.isNumber("123"));
        System.out.println(checker.isNumber("-42"));
        System.out.println(checker.isNumber("12abc"));
        System.out.println(checker.isNumber(""));
        System.out.println(checker.isNumber(" 15 "));
        System.out.println(checker.isNumber(null));
    }
}
