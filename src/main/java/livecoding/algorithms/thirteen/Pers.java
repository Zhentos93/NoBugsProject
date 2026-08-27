package livecoding.algorithms.thirteen;

/*
 * Задача №13. Реализация Person с геттерами и сеттерами
 *
 * Создать класс Person с полями name и age,
 * геттерами, сеттерами и методом print().
 */

// Ошибки
/*
 * Ошибки в задаче №13:
 *
 * 1. print() не должен принимать параметры:
 *    public void print(String name, int age)
 *
 *    Объект уже хранит name и age в своих полях.
 *
 *    Нужно:
 *    public void print()
 *
 * 2. Пустой println() не выводит состояние объекта.
 *
 *    Нужно:
 *    System.out.println(name + ", " + age);
 *
 * 3. В main вместо пустых println() нужно вызывать:
 *    pers.print();
 */

public class Pers {
    private String name;
    private int age;

    public Pers(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void print() {
        System.out.println(name + ", " + age);
    }

    public static void main(String[] args) {
        Pers pers = new Pers("Alice", 25);

        pers.print();

        pers.setName("Bob");
        pers.setAge(30);

        System.out.println(pers.getName());
        System.out.println(pers.getAge());
        pers.print();
    }
}
