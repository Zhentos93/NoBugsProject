package livecoding.algorithms.twenty;

/*
 * Задача №20. Проверка строки на число
 *
 * Проверить, можно ли строку безопасно
 * преобразовать в целое число.
 */

// Ошибки
/*
 * Ошибки в задаче №20 — проверка строки на число:
 *
 * 1. Рабочий метод остался без реализации.
 *
 * 2. Не был использован Integer.parseInt(str)
 *    для попытки преобразования строки в int.
 *
 * 3. Потенциально ошибочное преобразование не было
 *    помещено в блок try.
 *
 * 4. Не был добавлен catch для NumberFormatException.
 */

public class NumberString {
    public NumberString() {
    }

    public boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        NumberString numberString = new NumberString();

        System.out.println(numberString.isNumber("123"));
        System.out.println(numberString.isNumber("-42"));
        System.out.println(numberString.isNumber("12abc"));
        System.out.println(numberString.isNumber(""));
        System.out.println(numberString.isNumber(" 15 "));
        System.out.println(numberString.isNumber(null));
    }
}
