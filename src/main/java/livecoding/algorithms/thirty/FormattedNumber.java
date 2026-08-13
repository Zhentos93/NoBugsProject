package livecoding.algorithms.thirty;

/*
 * Задача №30. Форматированный вывод чисел
 *
 * Вывести число с двумя знаками после запятой.
 */

// Ошибки
/*
 * Ошибки в задаче №30 — форматированный вывод чисел:
 *
 * 1. Метод был объявлен с возвращаемым типом double:
 *    public double print(double number)
 *
 *    По условию метод сам выводит результат и ничего
 *    не возвращает, поэтому нужен тип void.
 *
 * 2. Рабочий метод остался без реализации.
 *    Для двух знаков используется:
 *    System.out.printf("%.2f", number);
 *
 * 3. void-метод нельзя передавать в println():
 *    System.out.println(formattedNumber.print(...));
 *
 *    Его нужно вызвать отдельно, а затем при необходимости
 *    выполнить System.out.println() для перевода строки.
 */

public class FormattedNumber {
    public FormattedNumber() {
    }

    public void print(double number) {
        System.out.printf("%.2f", number);
    }

    public static void main(String[] args) {
        FormattedNumber formattedNumber = new FormattedNumber();

        formattedNumber.print(3.14159);
        System.out.println();

        formattedNumber.print(2.0);
        System.out.println();

        formattedNumber.print(1.999);
        System.out.println();
    }
}
