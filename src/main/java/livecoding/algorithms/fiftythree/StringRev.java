package livecoding.algorithms.fiftythree;

/*
 * Задача №53. Разворот строки
 *
 * Вернуть строку в обратном порядке.
 */

// Ошибки
/*
 * Ошибки в задаче №53 — разворот строки:
 *
 * 1. Пробелы удалялись через replaceAll(), хотя строку
 *    нужно только развернуть. Пробелы должны сохраниться.
 *
 * 2. Было написано "//s+".
 *    Для пробельных символов правильная запись — "\\s+",
 *    но в этой задаче replaceAll() вообще не требуется.
 *
 * 3. reverse() пытались вызвать у String.
 *    Этот метод принадлежит StringBuilder.
 *
 * 4. У методов отсутствовали круглые скобки:
 *    reverse.toString
 *
 *    Нужно:
 *    reverse().toString()
 *
 * 5. Метод должен вернуть String, поэтому после работы
 *    со StringBuilder требуется toString().
 */

public class StringRev {
    public StringRev() {
    }

    public String reverseString(String str) {

        return new StringBuilder(str)
                .reverse()
                .toString();
    }

    public static void main(String[] args) {
        StringRev stringRev = new StringRev();

        System.out.println(stringRev.reverseString("Java"));
        System.out.println(stringRev.reverseString("hello"));
        System.out.println(stringRev.reverseString("A"));
        System.out.println(stringRev.reverseString(""));
        System.out.println(stringRev.reverseString("Java 17"));
    }
}
