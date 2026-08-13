package livecoding.algorithms.twentyfour;

/*
 * Задача №24. Перечисление дней недели
 *
 * Создать enum Day с семью днями недели.
 */

public enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    public static void main(String[] args) {
        Day today = Day.MONDAY;

        System.out.println(today);
        System.out.println(Day.FRIDAY);

        for (Day day : Day.values()) { // Перебираем все значения enum
            System.out.println(day);
        }
    }
}
