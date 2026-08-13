package livecoding.algorithms.fourtyone;

/*
 * Задача №41. Сумма чисел в строке
 *
 * Найти сумму всех чисел,
 * встречающихся в строке.
 */

public class StringNumberSumCalculator {

    public StringNumberSumCalculator() {
    }

    public int sumNumbers(String str) {
        int sum = 0;
        int num = 0;

        for (char c : str.toCharArray()) { // Проверяем символы по очереди
            if (Character.isDigit(c)) {    // Текущий символ является цифрой
                num = num * 10 + (c - '0');
            } else {
                sum += num; // Добавляем закончившееся число к сумме
                num = 0;    // Начинаем собирать следующее число
            }
        }

        return sum + num; // Учитываем число в конце строки
    }

    public static void main(String[] args) {
        StringNumberSumCalculator calculator =
                new StringNumberSumCalculator();

        System.out.println(
                calculator.sumNumbers("abc12def3")
        );
        System.out.println(
                calculator.sumNumbers("10 apples and 25 pears")
        );
        System.out.println(
                calculator.sumNumbers("1a2b3")
        );
        System.out.println(
                calculator.sumNumbers("abc")
        );
        System.out.println(
                calculator.sumNumbers("42")
        );
        System.out.println(
                calculator.sumNumbers("")
        );
    }
}
