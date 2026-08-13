package livecoding.algorithms.twentyfour;

/*
 * Задача №24. Перечисление дней недели
 *
 * Создать enum Day с семью днями недели.
 */

// Ошибки
/*
 * Ошибки в задаче №24 — перечисление дней недели:
 *
 * 1. В правой части for-each было указано имя типа:
 *    for (Da day : Da)
 *
 *    Нужно получить массив всех значений enum:
 *    for (Da day : Da.values())
 *
 * 2. Пропущен отдельный вывод пятницы:
 *    System.out.println(Da.FRIDAY);
 */

public enum Da {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public static void main(String[] args) {
        Da today = Da.MONDAY;

        System.out.println(today);
        System.out.println(FRIDAY);

        for (Da day : Da.values()) {
            System.out.println(day);
        }
    }
}
