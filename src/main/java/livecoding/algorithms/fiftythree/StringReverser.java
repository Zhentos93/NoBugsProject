package livecoding.algorithms.fiftythree;

/*
 * Задача №53. Разворот строки
 *
 * Вернуть строку в обратном порядке.
 */

public class StringReverser {

    public StringReverser() {
    }

    public String reverseString(String s) {
        return new StringBuilder(s)
                .reverse()   // Разворачиваем символы
                .toString(); // Преобразуем результат в String
    }

    public static void main(String[] args) {
        StringReverser reverser = new StringReverser();

        System.out.println(
                reverser.reverseString("Java")
        );
        System.out.println(
                reverser.reverseString("hello")
        );
        System.out.println(
                reverser.reverseString("A")
        );
        System.out.println(
                reverser.reverseString("")
        );
        System.out.println(
                reverser.reverseString("Java 17")
        );
    }
}
