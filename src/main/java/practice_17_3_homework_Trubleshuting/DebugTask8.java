// Код должен сравнить два числа, но почему-то результат не соответствует ожиданиям.

package practice_17_3_homework_Trubleshuting;

public class DebugTask8 {
    public static void main(String[] args) {
        double a = 0.1 * 3;
        double result = Math.round(a * 10) / 10.0;
        double b = 0.3;
        if (result == b) {
            System.out.println("Equal");
        } else {
            System.out.println("Not Equal");
        }

        // сравнение через epsilon - best practice в Java.
        /**
         * double a = 0.1 * 3;
         * double b = 0.3;
         *
         * if (Math.abs(a - b) < 0.000001) {
         *     System.out.println("Equal");
         * } else {
         *     System.out.println("Not Equal");
         * }
         */

        /**
         * «Как правильно сравнивать double?»
         * Не использовать ==.
         * Сравнивать через допустимую погрешность (epsilon), например:
         *
         * Math.abs(a - b) < 1e-6
         *
         * Если нужны абсолютно точные вычисления (например, деньги), использовать BigDecimal.
         */
    }
}

/* было
    double a = 0.1 * 3;
    double result = Math.round(a * 10) / 10.0;
    double b = 0.3;
*/
