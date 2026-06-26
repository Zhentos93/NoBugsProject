// Напишите тесты для метода, который определяет, является ли год високосным:

/*
Тесты должны проверять:
Обычные годы
Високосные (2020, 2000, 1600)
Года, которые делятся на 100, но не на 400 (1900, 2100)
*/

package practice_16_3_homework_WritingTests;

public class CheckLeapYear {

    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
