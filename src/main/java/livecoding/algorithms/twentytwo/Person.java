package livecoding.algorithms.twentytwo;

/*
 * Задача №22. Проверка возраста с использованием объекта
 *
 * Создать метод isAdult() в классе Person,
 * возвращающий true, если возраст не меньше 18.
 */

public class Person {

    private int age; // Возраст человека

    public Person(int age) {
        this.age = age; // Сохраняем возраст в поле объекта
    }

    boolean isAdult() {
        return this.age >= 18; // Проверяем достижение совершеннолетия
    }

    public static void main(String[] args) {
        Person adult = new Person(18);
        Person child = new Person(17);
        Person olderPerson = new Person(35);

        System.out.println(adult.isAdult());
        System.out.println(child.isAdult());
        System.out.println(olderPerson.isAdult());
    }
}
