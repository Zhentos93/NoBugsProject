package livecoding.algorithms.thirtyfive;

/*
 * Задача №35. Удаление всех пробелов из строки
 *
 * Удалить все пробельные символы из строки,
 * включая начало, середину и конец.
 */

// Ошибки
/*
 * Ошибок, влияющих на результат, нет.
 *
 * Расхождение с примером:
 *    "\\s"  — находит один пробельный символ;
 *    "\\s+" — находит группу из одного или нескольких
 *              пробельных символов.
 *
 * В примере задачи используется:
 *    return s.replaceAll("\\s+", "");
 */

public class Whitespace {
    public Whitespace() {
    }

    public String removeSpaces(String s) {
        return s.replaceAll("\\s+", "");
    }

    public static void main(String[] args) {
        Whitespace whitespace = new Whitespace();

        System.out.println(whitespace.removeSpaces("  Java is fun  "));
        System.out.println(whitespace.removeSpaces("Java\tis\nfun"));
        System.out.println(whitespace.removeSpaces("Java"));
        System.out.println(whitespace.removeSpaces("   "));
        System.out.println(whitespace.removeSpaces(""));
    }
}
