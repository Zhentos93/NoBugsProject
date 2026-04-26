/*Условие задачи:
    Разработайте дженерик класс Box, который может хранить объекты любого типа.
    Класс должен иметь методы для установки и получения значения объекта, хранящегося внутри.*/

package practice_14_homework_Exceptions_Generics.generics;

public class Box<T> {

    private T item;

    public void set(T item) {
        this.item = item;
    }

    public T get() {
        return this.item;
    }

    public static void main(String[] args) {
        Box<String> boxString = new Box<>();
        boxString.set("Hello, World!");
        System.out.println(boxString.get());

        Box<Integer> boxInteger = new Box<>();
        boxInteger.set(1024);
        System.out.println(boxInteger.get());
    }
}
