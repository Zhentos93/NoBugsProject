package livecoding.algorithms.fifty;

/*
 * Задача №50. Удалить пробелы
 *
 * Удалить все обычные пробелы из строки.
 */

public class SpaceRemover {

    public SpaceRemover() {
    }

    public String removeSpaces(String str) {
        return str.replaceAll(" ", ""); // Заменяем пробелы на пустую строку
    }

    public static void main(String[] args) {
        SpaceRemover remover = new SpaceRemover();

        System.out.println(
                remover.removeSpaces("Java is fun")
        );
        System.out.println(
                remover.removeSpaces("  Java  ")
        );
        System.out.println(
                remover.removeSpaces("Java")
        );
        System.out.println(
                remover.removeSpaces("   ")
        );
        System.out.println(
                remover.removeSpaces("")
        );
    }
}