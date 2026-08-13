package livecoding.algorithms.thirtyfive;

/*
 * Задача №35. Удаление всех пробелов из строки
 *
 * Удалить все пробельные символы из строки,
 * включая начало, середину и конец.
 */

public class WhitespaceRemover {

    public WhitespaceRemover() {
    }

    String removeSpaces(String s) {
        return s.replaceAll("\\s+", ""); // Заменяем пробельные группы на пустоту
    }

    public static void main(String[] args) {
        WhitespaceRemover remover = new WhitespaceRemover();

        System.out.println(remover.removeSpaces("  Java is fun  "));
        System.out.println(remover.removeSpaces("Java\tis\nfun"));
        System.out.println(remover.removeSpaces("Java"));
        System.out.println(remover.removeSpaces("   "));
        System.out.println(remover.removeSpaces(""));
    }
}
