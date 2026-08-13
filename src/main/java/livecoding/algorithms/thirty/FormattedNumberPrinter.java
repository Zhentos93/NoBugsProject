package livecoding.algorithms.thirty;

/*
 * Задача №30. Форматированный вывод чисел
 *
 * Вывести число с двумя знаками после запятой.
 */

public class FormattedNumberPrinter {

    public FormattedNumberPrinter() {
    }

    void print(double number) {
        System.out.printf("%.2f", number); // Выводим два знака после разделителя
    }

    public static void main(String[] args) {
        FormattedNumberPrinter printer =
                new FormattedNumberPrinter();

        printer.print(3.14159);
        System.out.println();

        printer.print(2.0);
        System.out.println();

        printer.print(1.999);
        System.out.println();
    }
}