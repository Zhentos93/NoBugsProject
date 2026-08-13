package livecoding.algorithms.twentytwo;

/*
 * Задача №22. Проверка возраста с использованием объекта
 *
 * Создать метод isAdult() в классе Person,
 * возвращающий true, если возраст не меньше 18.
 */

// Ошибок при решении нет

public class Pers {
    private int age;

    public Pers(int age) {
        this.age = age;
    }

    public boolean isAdult() {
        return this.age >= 18;
    }

    public static void main(String[] args) {
        Pers pers = new Pers(18);
        System.out.println(pers.isAdult());

        Pers pers1 = new Pers(17);
        System.out.println(pers1.isAdult());

        Pers pers2 = new Pers(35);
        System.out.println(pers2.isAdult());
    }
}
