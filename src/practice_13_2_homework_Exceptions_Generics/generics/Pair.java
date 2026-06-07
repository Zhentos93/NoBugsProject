/*Условие задачи:
    Создайте класс Pair, который может хранить два объекта разных типов.
    Класс должен позволять получать и устанавливать каждый из этих объектов.*/

package practice_13_2_homework_Exceptions_Generics.generics;

public class Pair <T,U> {
    private T first;
    private U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T item) {
        this.first = item;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U item) {
        this.second = item;
    }

    @Override
    public String toString() {
        return "{" + first + ", " + second + "}";
    }

    public static void main(String[] args) {
        Pair<String, Integer> pair = new Pair<>("Hello", 10);
        System.out.println(pair.toString());
    }
}
