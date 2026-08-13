package livecoding.algorithms.fifty;

/*
 * Задача №50. Удалить пробелы
 *
 * Удалить все обычные пробелы из строки.
 */

// Ошибки
/*
 * Ошибки в задаче №50 — удалить пробелы:
 *
 * 1. В первой попытке рабочий метод остался без реализации,
 *    потому что не был вспомнен метод replaceAll().
 *
 *    Нужно:
 *    return s.replaceAll(" ", "");
 *
 * После подсказки метод написан без ошибок.
 */

public class SpaceRem {
    public SpaceRem() {
    }

    public String removeSpaces(String s) {
        return s.replaceAll(" ", "");
    }

    public static void main(String[] args) {
        SpaceRem spaceRem = new SpaceRem();

        System.out.println(spaceRem.removeSpaces("Java is fun"));
        System.out.println(spaceRem.removeSpaces("  Java  "));
        System.out.println(spaceRem.removeSpaces("Java"));
        System.out.println(spaceRem.removeSpaces("   "));
        System.out.println(spaceRem.removeSpaces(""));
    }
}
