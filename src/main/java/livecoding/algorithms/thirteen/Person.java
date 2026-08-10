package livecoding.algorithms.thirteen;

/*
 * Задача №13. Реализация Person с геттерами и сеттерами
 *
 * Создать класс Person с полями name и age,
 * геттерами, сеттерами и методом print().
 */

public class Person {

    private String name; // Имя человека
    private int age;     // Возраст человека

    public Person(String n, int a) {
        name = n; // Сохраняем переданное имя
        age = a;  // Сохраняем переданный возраст
    }

    public String getName() {
        return name; // Возвращаем имя
    }

    public void setName(String n) {
        name = n; // Изменяем имя
    }

    public int getAge() {
        return age; // Возвращаем возраст
    }

    public void setAge(int a) {
        age = a; // Изменяем возраст
    }

    public void print() {
        System.out.println(name + ", " + age); // Выводим состояние объекта
    }

    public static void main(String[] args) {
        Person person = new Person("Alice", 25);

        person.print();

        person.setName("Bob");
        person.setAge(30);

        System.out.println(person.getName());
        System.out.println(person.getAge());
        person.print();
    }
}
